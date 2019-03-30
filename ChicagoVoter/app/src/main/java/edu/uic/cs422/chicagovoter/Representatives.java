package edu.uic.cs422.chicagovoter;

public class Representatives
{
    String repName;
    String office;
    Integer imgID;
    String website;
    String phoneNumber;
    String party;
    String reElection;
    String email;

    public Representatives(String repName, String office, Integer imgID, String website, String email, String phoneNumber, String party, String reElection)
    {
        this.email = email;
        this.repName = repName;
        this.office = office;
        this.imgID = imgID;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.party = party;
        this.reElection = reElection;
    }
}
