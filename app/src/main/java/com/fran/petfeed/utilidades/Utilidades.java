package com.fran.petfeed.utilidades;

public class Utilidades {

    //Constantes campos tabla usuario
    public static final String TABLA_USUARIO="usuario";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_TELEFONO="telefono";

    //Constantes campos tabla mascota
    public static final String TABLA_MASCOTA="mascota";
    public static final String CAMPO_ID_MASCOTA="id";
    public static final String CAMPO_NOMBRE_MASCOTA="nombre";
    public static final String CAMPO_GENERO="genero";
    public static final String CAMPO_TIPO="tipo";
    public static final String CAMPO_RAZA="raza";
    public static final String CAMPO_TAMANO="tamano";
    public static final String CAMPO_CUMPLEANOS="cumpleanos";
    public static final String CAMPO_CELO="celo";
    public static final String CAMPO_IDDUENO="iddueno";

    //Constantes campos tabla enfermedad
    public static final String TABLA_ENFERMEDAD="enfermedad";
    public static final String CAMPO_ID_ENFERMEDAD="id";
    public static final String CAMPO_NOMBRE_ENFERMEDAD="nombre";
    public static final String CAMPO_EVITAR="evitar";
    public static final String CAMPO_AFECTA_A="afecta_a";
    public static final String CAMPO_IDMASCOTA="idmascota";

    //Constantes campos tabla alimento
    public static final String TABLA_ALIMENTO="alimento";
    public static final String CAMPO_ID_ALIMENTO="id";
    public static final String CAMPO_NOMBRE_ALIMENTO="nombre";
    public static final String CAMPO_DESCRIPCION="descripcion";
    public static final String CAMPO_TIPOANIMAL="tipoanimal";
    public static final String CAMPO_TOXICIDAD="toxicidad";
    public static final String CAMPO_COMPUESTO="compuesto";


    public static final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLA_USUARIO+" ("
            +CAMPO_ID+" INTEGER PRIMARY KEY, "
            +CAMPO_NOMBRE+" TEXT, "
            +CAMPO_TELEFONO+" TEXT)";

    public static final String CREAR_TABLA_MASCOTA="CREATE TABLE "+TABLA_MASCOTA+" ("
            +CAMPO_ID_MASCOTA+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE_MASCOTA+" TEXT, "
            +CAMPO_GENERO+" TEXT, "
            +CAMPO_TIPO+" TEXT, "
            +CAMPO_RAZA+" TEXT, "
            +CAMPO_TAMANO+" TEXT, "
            +CAMPO_CUMPLEANOS+" TEXT, "
            +CAMPO_CELO+" TEXT, "
            +CAMPO_IDDUENO+" INTEGER, "
            +"FOREIGN KEY(" +CAMPO_IDDUENO+") REFERENCES "+TABLA_USUARIO+"("+CAMPO_ID+"))";

    public static final String CREAR_TABLA_ENFERMEDAD="CREATE TABLE "+TABLA_ENFERMEDAD+" ("
            +CAMPO_ID_ENFERMEDAD+"  INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE_ENFERMEDAD+" TEXT, "
            +CAMPO_EVITAR+" TEXT,"
            +CAMPO_AFECTA_A+" TEXT, "
            +CAMPO_IDMASCOTA+" INTEGER, "
            +"FOREIGN KEY(" +CAMPO_IDMASCOTA+") REFERENCES "+TABLA_MASCOTA+"("+CAMPO_ID_MASCOTA+"))";

    public static final String CREAR_TABLA_ALIMENTO="CREATE TABLE "+TABLA_ALIMENTO+" ("
            +CAMPO_ID_ALIMENTO+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE_ALIMENTO+" TEXT, "
            +CAMPO_DESCRIPCION+" TEXT, "
            +CAMPO_TIPOANIMAL+" TEXT, "
            +CAMPO_TOXICIDAD+" TEXT,"
            +CAMPO_COMPUESTO+" TEXT)";

    public static final String INSERT_ALIMENTOS="INSERT INTO "+TABLA_ALIMENTO+" ("
            +CAMPO_NOMBRE_ALIMENTO+","+CAMPO_DESCRIPCION+","+CAMPO_TIPOANIMAL+","+CAMPO_TOXICIDAD+","+CAMPO_COMPUESTO+") VALUES "
            +"('Croquetas', 'Genericas, cualquier marca segun el tama??o', 'Perro', 'No', 'Varios'), "
            +"('Croquetas', 'Genericas, cualquier marca segun el tama??o', 'Gato', 'No', 'Varios'), "
            +"('Manteca de cacahuete', 'Cruda y sin sal', 'Perro', 'No', 'Grasas saluables'), "
            +"('Pollo', 'Cocido, reemplazo de comida para apuros, prote??na extra', 'Perro', 'No', 'Prote??na'), "
            +"('Queso', 'Queso de caba??a, variedades bajas en grasas', 'Perro', 'No', 'Lactosa'), "
            +"('Zanahoria', 'Buenas para los dientes', 'Perro', 'No', 'Fibra'), "
            +"('Yogurt', 'Sin edulcorantes artificiales o azucares a??adidos, calcio y proteinas', 'Perro', 'No', 'Lactosa'), "
            +"('Salm??n', 'Cocinado o en forma de aceite de salmon, acido graso y omega 3', 'Perro', 'No', 'Prote??na'), "
            +"('Calabaza', 'Buena fuente de fibra y de beta-caroteno/vitamina A', 'Perro', 'No', 'Fibra'), "
            +"('Huevo', 'Cocinado, Fuente de riboflavina y selenio que son f??cil de digerir', 'Perro', 'No', 'Prote??na'), "
            +"('Ejotes', 'Sin sal, Tienen mucha fibra y son bajos en calor??as', 'Perro', 'No', 'Fibra'), "
            +"('Manzana', 'Sin semillas y nucleo, ayudan a limpiar los residuos de los dientes', 'Perro', 'No', 'Fibra'), "
            +"('Avena', 'Cocida y sin azucar, Beneficiosa para los perros mayores', 'Perro', 'No', 'Fibra'), "
            +"('Aguacate', 'Puede provocar diarrea, v??mitos, edema pulmonar y da??o card??aco', 'Perro', 'Si', 'Persina'), "
            +"('Cebolla y ajo', 'Destruir los gl??bulos rojos, provoca anemia', 'Perro', 'Si', 'Fibra'), "
            +"('Alcohol', 'Puede causar la muerte por coma et??lico', 'Perro', 'Si', 'Etanol'), "
            +"('Cafe', 'Causa taquicardia, nerviosismo o hiperactividad', 'Perro', 'Si', 'Cafe??na'), "
            +"('Chocolate', 'Produce de v??mitos y diarrea hasta la muerte', 'Perro', 'Si', 'Teobromina'), "
            +"('Huevos crudos', 'Causa salmonelosis', 'Perro', 'Si', 'Proteina'), "
            +"('Nuez', 'Provoca c??lculos en la vejiga adem??s de otros s??ntomas', 'Perro', 'Si', 'Fosforo'), "
            +"('Leche', 'Bajas dosis', 'Perro', 'No', 'Lactosa'), "
            +"('Uva', 'Provoca v??mitos y diarrea', 'Perro', 'Si', 'Calorias'), "
            +"('Patatas crudas', 'Provoca gastroenteritis hasta insuficiencias renales ', 'Perro', 'Si', 'Carbohidrato'), "
            +"('Cebolla y ajo', 'Destruir los gl??bulos rojos, provoca anemia', 'Gato', 'Si', 'Fibra'), "
            +"('Huevos crudos y carne cruda', 'Causa salmonelosis', 'Gato', 'Si', 'Proteina'), "
            +"('Huesos', 'Causa ahogamiento, da??a el tracto digestivo o los dientes', 'Gato', 'Si', 'Proteina'), "
            +"('Chocolate', 'Produce de v??mitos y diarrea hasta la muerte', 'Gato', 'Si', 'Teobromina'), "
            +"('Cafe', 'Causa taquicardia, nerviosismo o hiperactividad', 'Gato', 'Si', 'Cafe??na'), "
            +"('Alcohol', 'Puede causar la muerte por coma et??lico', 'Gato', 'Si', 'Etanol'), "
            +"('Masa cruda', 'Causa expansi??n en el est??mago, o produce alcohol en el est??mago', 'Gato', 'Si', 'Harina'), "
            +"('Leche', 'Tienen problemas para digerir la lactosa', 'Gato', 'S??', 'Lactosa'), "
            +"('Uvas y pasas', 'Provoca v??mitos y diarrea', 'Gato', 'Si', 'Calorias'), "
            +"('Atun', 'Gusto amplio en gatos, incorporar mas complementos', 'Gato', 'No', 'Proteina'), "
            +"('Huevo', 'Cocinado, Fuente de riboflavina y selenio que son f??cil de digerir', 'Gato', 'No', 'Prote??na'), "
            +"('Higado', 'No abusar y comer con otros coplementos', 'Gato', 'No', 'Prote??na'), "
            +"('Carne', 'Cocida, taurina, ??cido araquid??nico y vitamina A', 'Gato', 'No', 'Prote??na') ";
}
