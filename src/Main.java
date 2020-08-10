
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import paymethods.Paypal;
import products.components.Storage;
import products.databases.DatabaseAgregator;
import products.databases.ProductDatabase;
import products.databases.UserDatabase;
import products.hostings.Hosting;
import products.hostings.WpHosting;
import products.servers.ServerObserver;
import products.servers.ServerStateVisitor;
import products.servers.Vps;

public class Main extends Application {
    String vpsSerializationDestination = "/home/jakub/Documents/oop/oopProject/project/src/products/" +
            "serializations/VpsDb.ser";

    @Override
    public void start(Stage stage) throws Exception {
        try {

            ProductDatabase vpsDatabase;
            ProductDatabase hostingDatabase;


            // Reading the object from a file
            FileInputStream vpsFile = new FileInputStream
                    ("/home/jakub/Documents/oop/oopProject/project/src/products/serializations/VpsDb.ser");
            ObjectInputStream vpsIn = new ObjectInputStream
                    (vpsFile);

            // Method for deserialization of object
            vpsDatabase = (ProductDatabase) vpsIn.readObject();
            vpsIn.close();
            vpsFile.close();
            DatabaseAgregator.setVpsDb(vpsDatabase);

            FileInputStream hostingFile = new FileInputStream
                    ("/home/jakub/Documents/oop/oopProject/project/src/products/serializations/HostingDb.ser");
            ObjectInputStream hostingIn = new ObjectInputStream(hostingFile);

            hostingDatabase = (ProductDatabase) hostingIn.readObject();
            hostingIn.close();
            hostingFile.close();
            DatabaseAgregator.setHostingDb(hostingDatabase);


            System.out.println("Object has been deserialized\n");


        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/MainMenuScene.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();




    }

    @Override
    public void stop() throws Exception {

        try {
            FileOutputStream vpsFile = new FileOutputStream
                    ("/home/jakub/Documents/oop/oopProject/project/src/products/serializations/VpsDb.ser");
            ObjectOutputStream vpsOut = new ObjectOutputStream(vpsFile);
            vpsOut.writeObject(DatabaseAgregator.getVpsDb());
            vpsFile.close();
            FileOutputStream hostingFile = new FileOutputStream
                    ("/home/jakub/Documents/oop/oopProject/project/src/products/serializations/HostingDb.ser");
            ObjectOutputStream hostingOut = new ObjectOutputStream(hostingFile);
            hostingOut.writeObject(DatabaseAgregator.getHostingDb());
            hostingFile.close();
            System.out.println("Objects have been serialized..\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        super.stop();
    }

    public static void main(String[] args) {


        Vps vps1 = new Vps(DatabaseAgregator.getVpsDb(), 512, Storage.TypeOfStorage.SSD, 120,
                products.components.ServerComponents.Os.Distributions.DEBIAN);
            vps1.setOs(products.components.ServerComponents.Os.Distributions.DEBIAN);


        Hosting hosting1 = new Hosting(DatabaseAgregator.getHostingDb());
        hosting1.addDomain("hnilica.sk");
        hosting1.addDb(UserDatabase.DbmsType.MYSQL, "dbhosting1", "dbhsoting1", "heslo");

        WpHosting hosting2 = new WpHosting(DatabaseAgregator.getHostingDb());
        hosting2.addDomain("domena.sk");
        hosting2.addDb(UserDatabase.DbmsType.POSTGRES, "dasasdads", "username", "heslo");

        ServerStateVisitor visitor1 = new ServerStateVisitor();
        ServerObserver observer1 = new ServerObserver(vps1, visitor1);
        vps1.setPaid(new Paypal());
        vps1.setPaid(false);

        HashMap<String, Object> map;
        map = DatabaseAgregator.getVpsDb().getMap();
        Iterator mapIterator = map.entrySet().iterator();
        while (mapIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)mapIterator.next();
            Vps server = (Vps)mapElement.getValue();
            System.out.println("Distro: " + server.getOsDistro() + "\n" + "Memory" + server.getServerRam() + "\n"
            + "Storage: " + server.getServerStorage() + "\n");
        }


        launch(args);
    }


}

