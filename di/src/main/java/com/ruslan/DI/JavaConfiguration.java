package java.com.ruslan.DI;

public class JavaConfiguration implements Configuration {

    @Override
    public String getPackageToScan() {
        return "com.ruslan";
    }

}
