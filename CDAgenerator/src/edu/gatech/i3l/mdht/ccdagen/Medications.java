package edu.gatech.i3l.mdht.ccdagen;
public class Medications
{
	String visitDate;
	String drug;
	String ndc;
	String quantity;
	String refills;
	String sig;

	public Medications(String visitDate, String drug, String ndc, String quantity, String refills, String sig)
	{
		this.visitDate = visitDate;
		this.drug = drug;
		this.ndc = ndc;
		this.quantity = quantity;
		this.refills = refills;
		this.sig = sig;
	}


}
