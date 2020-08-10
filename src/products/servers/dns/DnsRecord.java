package products.servers.dns;

import java.io.Serializable;

public class DnsRecord implements Serializable {

    private String a;
    private String cname;
    private String txt;
    private String mx;
    private String domainName;



    public DnsRecord(){}

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}
