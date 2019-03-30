package edu.uic.cs422.chicagovoter;

public class Candidates
{
    Integer imgId;
    String name;
    String officeRunningFor;
    String partyAffiliation;
    String contact;
    String website;

    public Candidates(Integer imgId, String name, String officeRunningFor, String partyAffiliation, String contact, String website)
    {
        this.imgId = imgId;
        this.name = name;
        this.officeRunningFor = officeRunningFor;
        this.partyAffiliation = partyAffiliation;
        this.contact = contact;
        this.website = website;
    }
}
