package products.databases;

import java.io.Serializable;

public class UsedUuidException extends Exception implements Serializable {
    public UsedUuidException(String message){
        super(message);
    }

    /**
     * In case randomly picked uuid is already used, this method tries to reverse it and check against uniqueness.
     * If it is unique, object is added with the uuid. If it is not, whole process repeat.
     * @param uuid candidate
     * @return reversed candidate to uuid (String)
     */
    public String revertUuuid(String uuid) {
        StringBuilder revUuid = new StringBuilder();
        revUuid.append(uuid);
        revUuid = revUuid.reverse();
        return revUuid.toString();
    }
}
