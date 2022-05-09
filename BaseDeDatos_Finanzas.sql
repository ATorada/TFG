CREATE TABLE unidad_familiar (
    id_unidad_familiar serial PRIMARY KEY
);

ALTER TABLE public.unidad_familiar
    OWNER TO usuario_finanzas;

CREATE TABLE usuario (
    usuario varchar(15) PRIMARY KEY,
    contra varchar(64) NOT NULL,
    ultimo_inicio TIMESTAMPTZ NOT NULL default CURRENT_TIMESTAMP,
    id_unidad_familiar int,
    CONSTRAINT unidad_familiar_constraint FOREIGN KEY (id_unidad_familiar) REFERENCES unidad_familiar(id_unidad_familiar) on update SET NULL on delete SET NULL
);

ALTER TABLE public.usuario
    OWNER TO usuario_finanzas;

CREATE TABLE compra_grande (
    usuario varchar(15) PRIMARY KEY,
    nombre varchar(50) NOT NULL,
    periodo DATE NOT NULL,
    cantidad NUMERIC(7,2) NOT NULL,
    CONSTRAINT usuario_compra_grande_constraint FOREIGN KEY (usuario) REFERENCES usuario(usuario) on update cascade on delete cascade
);

ALTER TABLE public.compra_grande
    OWNER TO usuario_finanzas;

CREATE TABLE finanzas (
    usuario varchar(15),
    concepto varchar(50) NOT NULL,
    periodo DATE NOT NULL default CURRENT_DATE,
    categoria varchar(50),
    cantidad NUMERIC(7,2) NOT NULL,
    constante boolean NOT NULL default false,
    es_ingreso boolean NOT NULL default false,
    computa_unidad_familiar boolean NOT NULL default true,
    PRIMARY KEY(usuario,concepto,periodo),
    CONSTRAINT usuario_finanzas_constraint FOREIGN KEY (usuario) REFERENCES usuario(usuario) on update cascade on delete cascade
);

ALTER TABLE public.finanzas
    OWNER TO usuario_finanzas;


CREATE OR REPLACE FUNCTION public.categoria_not_null_en_gasto()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
IF NEW.es_ingreso THEN
           NEW.categoria = NULL;
           RETURN NEW;
        ELSE
            IF(NEW.concepto = 'ahorro') AND (NEW.categoria != 'ahorro') THEN
            RAISE NOTICE 'El gasto de tipo ahorro no permite una categoría diferente a "ahorro".';
                ROLLBACK;
                RETURN NULL;
            END IF;
            IF (NEW.categoria = 'utilidades') OR (NEW.categoria = 'cuentas') OR (NEW.categoria = 'otros') THEN
                RETURN NEW;
            END IF;
            IF (NEW.concepto = 'ahorro') AND (NEW.categoria = 'ahorro') THEN
                RETURN NEW;
            ELSE
                RAISE NOTICE 'Los gastos no pueden tener una categoría nula, siendo "utilidades", "cuentas" u "otros" las únicas disponibles.';
                ROLLBACK;
                RETURN NULL;
            END IF;
        END IF;
END;
$BODY$;

ALTER FUNCTION public.categoria_not_null_en_gasto()
    OWNER TO postgres;

CREATE TRIGGER categoria_not_null_en_gasto
    BEFORE INSERT OR UPDATE
    ON public.finanzas
    FOR EACH ROW
    EXECUTE FUNCTION public.categoria_not_null_en_gasto();


CREATE OR REPLACE FUNCTION public.unidad_familiar_con_un_usuario()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
IF NEW.id_unidad_familiar in (
            select usuario.id_unidad_familiar
            From usuario
        ) THEN
           RETURN NEW;
        ELSE
        RAISE NOTICE 'La unidad familiar tiene que tener mínimo un usuario.';
        ROLLBACK;
           RETURN NULL;
        END IF;
END;
$BODY$;

ALTER FUNCTION public.unidad_familiar_con_un_usuario()
    OWNER TO postgres;

CREATE CONSTRAINT TRIGGER unidad_familiar_con_un_usuario
    AFTER INSERT
    ON public.unidad_familiar
    DEFERRABLE INITIALLY DEFERRED
    FOR EACH ROW
    EXECUTE FUNCTION public.unidad_familiar_con_un_usuario();

CREATE OR REPLACE FUNCTION public.ultimo_usuario_unidad_familiar_delete()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
  DECLARE
  cuenta integer;
    cur cursor for select count(*) from usuario NATURAL JOIN unidad_familiar where id_unidad_familiar = old.id_unidad_familiar; 
  BEGIN
   
   OPEN cur;
    LOOP
         FETCH cur INTO cuenta;
         EXIT WHEN NOT FOUND;
     if (cuenta=0) then
	           CLOSE cur;
          DELETE FROM public.unidad_familiar WHERE id_unidad_familiar=old.id_unidad_familiar;
          DELETE FROM public.usuario WHERE usuario=old.usuario;
		  return NULL;
     else
	      CLOSE cur;
          RETURN OLD;
     END IF;
    END LOOP;
	CLOSE cur;
   RETURN NULL;
  END;
 
$BODY$;

ALTER FUNCTION public.ultimo_usuario_unidad_familiar_delete()
    OWNER TO postgres;


CREATE TRIGGER ultimo_usuario_unidad_familiar_delete
    AFTER DELETE
    ON public.usuario
    FOR EACH ROW
    EXECUTE FUNCTION public.ultimo_usuario_unidad_familiar_delete();

CREATE OR REPLACE FUNCTION public.ultimo_usuario_unidad_familiar_update()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
  DECLARE
  cuenta integer;
    cur cursor for select count(*) from usuario NATURAL JOIN unidad_familiar where id_unidad_familiar = old.id_unidad_familiar; 
  BEGIN
   
   OPEN cur;
    LOOP
         FETCH cur INTO cuenta;
         EXIT WHEN NOT FOUND;
     if (cuenta=0) then
	           CLOSE cur;
          DELETE FROM public.unidad_familiar WHERE id_unidad_familiar=old.id_unidad_familiar;
		  return NULL;
     else
	      CLOSE cur;
          RETURN OLD;
     END IF;
    END LOOP;
	CLOSE cur;
   RETURN NULL;
  END;
 
$BODY$;

ALTER FUNCTION public.ultimo_usuario_unidad_familiar_update()
    OWNER TO postgres;


CREATE TRIGGER ultimo_usuario_unidad_familiar_update
    AFTER UPDATE
    ON public.usuario
    FOR EACH ROW
    EXECUTE FUNCTION public.ultimo_usuario_unidad_familiar_update();


CREATE OR REPLACE FUNCTION public.formatear_fecha()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
IF (NEW.periodo IS NOT NULL) THEN
		   NEW.periodo = cast(concat(date_part('year', NEW.periodo),'-', date_part('month', NEW.periodo),'-',1) as date);
           RETURN NEW;
        ELSE
           RAISE NOTICE 'El periodo es nulo.';
        ROLLBACK;
           RETURN NULL;
        END IF;
END;
$BODY$;

ALTER FUNCTION public.formatear_fecha()
    OWNER TO postgres;
	
CREATE TRIGGER formatear_fecha_finanzas
    BEFORE INSERT OR UPDATE
    ON public.finanzas
    FOR EACH ROW
    EXECUTE FUNCTION public.formatear_fecha();
	
CREATE TRIGGER formatear_fecha_compra_grande
    BEFORE INSERT OR UPDATE
    ON public.compra_grande
    FOR EACH ROW
    EXECUTE FUNCTION public.formatear_fecha();

CREATE OR REPLACE FUNCTION public.ultimo_inicio_sesion()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
  BEGIN
   NEW.ultimo_inicio = CURRENT_TIMESTAMP;
   RETURN NEW;
  END;
 
$BODY$;

ALTER FUNCTION public.ultimo_inicio_sesion()
    OWNER TO postgres;


CREATE TRIGGER ultimo_inicio_sesion
    BEFORE INSERT OR UPDATE
    ON public.usuario
    FOR EACH ROW
    EXECUTE FUNCTION public.ultimo_inicio_sesion();

CREATE OR REPLACE FUNCTION public.limite_cinco_usuarios_unidad_familiar()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
  DECLARE
  cuenta integer;
    cur cursor for select count(*) from usuario NATURAL JOIN unidad_familiar where id_unidad_familiar = new.id_unidad_familiar; 
  BEGIN
   
   OPEN cur;
    LOOP
         FETCH cur INTO cuenta;
         EXIT WHEN NOT FOUND;
     if (cuenta>=5) then
	           CLOSE cur;
          ROLLBACK;
		  return NULL;
     else
	      CLOSE cur;
          RETURN NEW;
     END IF;
    END LOOP;
	CLOSE cur;
   RETURN NULL;
  END;
 
$BODY$;

ALTER FUNCTION public.limite_cinco_usuarios_unidad_familiar()
    OWNER TO postgres;


CREATE TRIGGER limite_cinco_usuarios_unidad_familiar
    BEFORE INSERT OR UPDATE
    ON public.usuario
    FOR EACH ROW
    EXECUTE FUNCTION public.limite_cinco_usuarios_unidad_familiar();

