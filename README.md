# AppAvisos
App para publicar y consultar avisos para una comunidad. Emplea ROOM, Firebase (Firestore, Autentication). Construida con MVVM, Live Data y Corutinas

* La aplicación consume una base de datos de avisos en Firebase y muestra un listado de categorías
* Desde el listado de categorías se accede a un listado de avisos
* En el detalle de cada aviso se puede:
    a) Compartir detalles del aviso a través de otras apps (SMS, email, WhatsApp)
    b) Llamar al que publica el aviso, si ha dejado un teléfono de contacto
    c) Evaluar el servicio publicado
    
* Para evaluar el servicio publicado es necesario identificarse. Esto se realiza mediante la identificación con una cuenta Google, empleando Google SignIn Services
* Las evaluaciones se guardan en una base de datos remotas en Firebase
* La aplicación emplea SQLite-Room para guardar y consultar los datos localmente en el caso de que se interrumpa la conexión a Internet.
