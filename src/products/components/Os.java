package products.components;

import java.io.Serializable;

public class Os implements Serializable {

    public enum Distributions{
        UBUNTU,
        DEBIAN,
        CENTOS,
        CLEAR_INSTALL;


    }

    private Distributions distro;
    private String version;
    private boolean upgrade;

    public String getStringDistro() {
        return this.distro.toString();
    }

    public Distributions getDistro() {
        return distro;
    }

    public void setDistro(Distributions distro) {
        this.distro = distro;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isUpgrade() {
        return upgrade;
    }

    public void setUpgrade(boolean upgrade) {
        this.upgrade = upgrade;
    }
}