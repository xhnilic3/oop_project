package products.domains;

import java.io.Serializable;
import java.util.List;

public class MultiDomain implements Serializable {


    private Domain domain[] = new Domain[5];
    private int i = 0;

    public MultiDomain(){}

    /**
     * Add other domain to multidomain. If number of capable domain is exceeded, exception is thrown and is not possible
     * to add another domain
     * @param domain
     * @throws NumOfDomainsExceededException
     */
    public void addDomain(Domain domain) throws NumOfDomainsExceededException{
            if (this.i == this.domain.length)
                throw new NumOfDomainsExceededException("Number of domains within Multidomain exceeded");
            this.domain[i++] = domain;
    }
}
