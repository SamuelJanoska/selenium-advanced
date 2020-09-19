package enumerators;

public enum SinType {
    MURDER("murder"),
    HIJACK("hijack"),
    BLACKMAIL("blackmail"),
    CAR_ACCIDENT("car accident"),
    ROBBERY("robbery");

    private String xpathvalue;

    SinType(String xpathvalue){
        this.xpathvalue = xpathvalue;    }

    public String getXpathvalue() {
        return xpathvalue;    }
}
