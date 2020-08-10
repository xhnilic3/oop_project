package products.servers.dns;

import products.Product;

import java.io.Serializable;
import java.util.HashMap;

public class DnsServer implements Serializable {
    private HashMap<DnsRecord, String> map = new HashMap<>();

    public DnsServer(){
        System.out.println("DNS server successfully created..");
    }

    public void insertNewProduct(DnsRecord record){
        this.map.put(record, record.getDomainName());
    }

    public int findMatch(String uuid){
        for (String i : map.values()) {
            if (i == uuid)
                return 1;
        }
        return 0;
    }
}
