package products.domains;

import products.servers.dns.DnsRecord;

import java.io.Serializable;

public class Domain implements Serializable {
    private DnsRecord dns = new DnsRecord();

    public Domain(String domain){
        this.dns.setDomainName(domain);
    }

    public String getDomainName() { return this.dns.getDomainName(); }
}
