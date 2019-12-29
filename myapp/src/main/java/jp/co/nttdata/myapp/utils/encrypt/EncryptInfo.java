package jp.co.nttdata.myapp.utils.encrypt;

import java.io.Serializable;
import java.util.List;

public class EncryptInfo implements Serializable {

    private String packageName;

    private List<String> encryptNames;

    private String version;

    public EncryptInfo(String packageName, List<String> encryptNames, String version) {
        this.packageName = packageName;
        this.encryptNames = encryptNames;
        this.version = version;
    }

    public EncryptInfo() {
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getEncryptNames() {
        return encryptNames;
    }

    public void setEncryptNames(List<String> encryptNames) {
        this.encryptNames = encryptNames;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
