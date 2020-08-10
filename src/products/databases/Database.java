package products.databases;

import products.Product;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public abstract class Database implements Serializable {

    String uuid;
    HashMap<String, Object> map = new HashMap<>();

    public Database(DatabaseServer server){
        server.insertNewProduct(this);
        this.uuid = generateUuid();
    };

    public void insertNewProduct(Product product){
        this.map.put(product.getUuid(), product);
    }

    /**
     * Looking for match of uuid candidate and used uuid's across db
     * @param uuid
     * @return 1 in case match is occurred
     * @throws UsedUuidException
     */
    public int findMatch(String uuid) throws UsedUuidException {
        if(map.get(uuid) == null)
            return 0;
        throw new UsedUuidException("Uuid already taken");
    }

    /**
     * Generetaes uuid candidates
     * @return unique uuid candidate which will be used as unique identifier of product in db
     */
    public String generateUuid(){
        String uuidCandidate = generateRandomString();
        try {
            findMatch(uuidCandidate);
        } catch (UsedUuidException e) {
            // case candidate already occurs in db
            try {
                // try tu use candidate in reversed order of characters
                uuidCandidate = e.revertUuuid(uuidCandidate);
                findMatch(uuidCandidate);

            } catch (UsedUuidException ex) {
                // if reversed version of candidate occurs as well, repeat whole process
                return generateUuid();
            }
        }
        // if candidate or its reversed version does not occurs in db, it is used
        return uuidCandidate;
    }


    /**
     * Method used for generating of pseudo-random string used as candidate for uuid
     * @return uuid candidate
     */
    public String generateRandomString(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        // usage of lambdas
        String string = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return string;
    }

    public String getUuid() {
        return uuid;
    }

    public HashMap<String, Object> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Object> map) {
        this.map = map;
    }
}