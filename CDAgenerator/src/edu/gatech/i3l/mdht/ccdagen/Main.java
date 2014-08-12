/*******************************************************************************
 * Copyright (c) 2009, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*******************************************************************************
 * This java application reads CSV file and creates CDA documents in XML format. 
 *
 * @author	Sungwoo Han
 * @version	1.0
 *******************************************************************************/

package edu.gatech.i3l.mdht.ccdagen;

import java.math.BigDecimal;
import java.util.UUID;

import org.openhealthtools.mdht.uml.cda.Act;
import org.openhealthtools.mdht.uml.cda.AssignedAuthor;
import org.openhealthtools.mdht.uml.cda.AssignedCustodian;
import org.openhealthtools.mdht.uml.cda.AssignedEntity;
import org.openhealthtools.mdht.uml.cda.Author;
import org.openhealthtools.mdht.uml.cda.CDAFactory;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.Component1;
import org.openhealthtools.mdht.uml.cda.Component4;
import org.openhealthtools.mdht.uml.cda.Consumable;
import org.openhealthtools.mdht.uml.cda.Custodian;
import org.openhealthtools.mdht.uml.cda.CustodianOrganization;
import org.openhealthtools.mdht.uml.cda.DocumentationOf;
import org.openhealthtools.mdht.uml.cda.EncompassingEncounter;
import org.openhealthtools.mdht.uml.cda.Encounter;
import org.openhealthtools.mdht.uml.cda.Entry;
import org.openhealthtools.mdht.uml.cda.EntryRelationship;
import org.openhealthtools.mdht.uml.cda.InfrastructureRootTypeId;
import org.openhealthtools.mdht.uml.cda.ManufacturedProduct;
import org.openhealthtools.mdht.uml.cda.Material;
import org.openhealthtools.mdht.uml.cda.Observation;
import org.openhealthtools.mdht.uml.cda.Organizer;
import org.openhealthtools.mdht.uml.cda.Participant2;
import org.openhealthtools.mdht.uml.cda.ParticipantRole;
import org.openhealthtools.mdht.uml.cda.Patient;
import org.openhealthtools.mdht.uml.cda.PatientRole;
import org.openhealthtools.mdht.uml.cda.Performer1;
import org.openhealthtools.mdht.uml.cda.Person;
import org.openhealthtools.mdht.uml.cda.PlayingEntity;
import org.openhealthtools.mdht.uml.cda.RecordTarget;
import org.openhealthtools.mdht.uml.cda.ResponsibleParty;
import org.openhealthtools.mdht.uml.cda.Section;
import org.openhealthtools.mdht.uml.cda.ServiceEvent;
import org.openhealthtools.mdht.uml.cda.SubstanceAdministration;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.hl7.datatypes.AD;
import org.openhealthtools.mdht.uml.hl7.datatypes.ANY;
import org.openhealthtools.mdht.uml.hl7.datatypes.CD;
import org.openhealthtools.mdht.uml.hl7.datatypes.CE;
import org.openhealthtools.mdht.uml.hl7.datatypes.CS;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.ED;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVL_PQ;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVL_TS;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVXB_TS;
import org.openhealthtools.mdht.uml.hl7.datatypes.ON;
import org.openhealthtools.mdht.uml.hl7.datatypes.PIVL_TS;
import org.openhealthtools.mdht.uml.hl7.datatypes.PN;
import org.openhealthtools.mdht.uml.hl7.datatypes.ST;
import org.openhealthtools.mdht.uml.hl7.datatypes.TEL;
import org.openhealthtools.mdht.uml.hl7.datatypes.TS;
import org.openhealthtools.mdht.uml.hl7.vocab.ActClass;
import org.openhealthtools.mdht.uml.hl7.vocab.ActClassObservation;
import org.openhealthtools.mdht.uml.hl7.vocab.ActMood;
import org.openhealthtools.mdht.uml.hl7.vocab.ActRelationshipType;
import org.openhealthtools.mdht.uml.hl7.vocab.EntityClassRoot;
import org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor;
import org.openhealthtools.mdht.uml.hl7.vocab.ParticipationType;
import org.openhealthtools.mdht.uml.hl7.vocab.PostalAddressUse;
import org.openhealthtools.mdht.uml.hl7.vocab.RoleClassManufacturedProduct;
import org.openhealthtools.mdht.uml.hl7.vocab.RoleClassRoot;
import org.openhealthtools.mdht.uml.hl7.vocab.SetOperator;
import org.openhealthtools.mdht.uml.hl7.vocab.TelecommunicationAddressUse;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ActClassDocumentEntryAct;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ActClassDocumentEntryOrganizer;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ActMoodDocumentObservation;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ActRelationshipEntry;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ActRelationshipEntryRelationship;
import org.openhealthtools.mdht.uml.hl7.vocab.x_DocumentActMood;
import org.openhealthtools.mdht.uml.hl7.vocab.x_DocumentEncounterMood;
import org.openhealthtools.mdht.uml.hl7.vocab.x_DocumentSubstanceMood;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ServiceEventPerformer;

public class Main {

	public static void main(String[] args) throws Exception {

		String csvFile = "";
		if (args.length == 0) {
			// Add code to give error message that there was no inputFile argument
			csvFile = "proposed CSVformat2_4.csv";
		} else {
			csvFile = args[0];
		}
		ReadDemog readDemog = new ReadDemog(csvFile);
		ReadDoc readDoc = new ReadDoc(csvFile);
		ReadEcns readEcns = new ReadEcns(csvFile);
		ReadImmu readImmu = new ReadImmu(csvFile);
		ReadLabT readLabT = new ReadLabT(csvFile);
		ReadProbs readProbs = new ReadProbs(csvFile);
		ReadMed readMed = new ReadMed(csvFile);
		ReadHist readHist = new ReadHist(csvFile);
		ReadAuthor readAuthor = new ReadAuthor(csvFile);
		ReadCustodian readCustodian = new ReadCustodian(csvFile);
		ReadAlg readAlg = new ReadAlg(csvFile);
		ReadVitals readVitals = new ReadVitals(csvFile);

		ClinicalDocument doc = CDAFactory.eINSTANCE.createClinicalDocument();
		// create document.

		CS Realm = DatatypesFactory.eINSTANCE.createCS();
		Realm.setCode("US");
		doc.getRealmCodes().add(Realm);

		InfrastructureRootTypeId typeId = CDAFactory.eINSTANCE.createInfrastructureRootTypeId();
		// create InfrastructureRootTypeID

		typeId.setExtension("POCD_HD000040");
		// set Extension on the InfrastructureRootTypeID

		doc.setTypeId(typeId);
		// set TypeID in the document

		II templateId = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.1.1");
		doc.getTemplateIds().add(templateId);

		II templateId2 = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.1.2");
		doc.getTemplateIds().add(templateId2);
		// set TemplateID

		II id = DatatypesFactory.eINSTANCE.createII("1.1.1.1.1.1.1.1.1", "Test CCDA");
		doc.setId(id);
		// ID and Extension!!!

		CE code = DatatypesFactory.eINSTANCE.createCE(
			"34133-9", "2.16.840.1.113883.6.1", "LOINC", "Summarization of Episode Note");
		doc.setCode(code);
		// set Code

		ST title = DatatypesFactory.eINSTANCE.createST("Community Health and Hospitals: Health Summary(Example)");
		doc.setTitle(title);
		// set an example title here

		TS effectiveTime = DatatypesFactory.eINSTANCE.createTS("20140701000000-0000");
		doc.setEffectiveTime(effectiveTime);
		// set effective time

		CE confidentialityCode = DatatypesFactory.eINSTANCE.createCE("N", "2.16.840.1.113883.5.25");
		doc.setConfidentialityCode(confidentialityCode);

		CS language = DatatypesFactory.eINSTANCE.createCS();
		language.setCode("en-US");
		doc.setLanguageCode(language);
		// set confidentiality code

		RecordTarget recordTarget = CDAFactory.eINSTANCE.createRecordTarget();

		doc.getRecordTargets().add(recordTarget);
		// Record Target

		PatientRole patientRole = CDAFactory.eINSTANCE.createPatientRole();
		recordTarget.setPatientRole(patientRole); // Created PatientRole and added!!!

		II PatientId = DatatypesFactory.eINSTANCE.createII();
		patientRole.getIds().add(PatientId);
		PatientId.setExtension("123-456-7890");
		PatientId.setRoot("2.16.840.1.113883.4.1");

		// add patient ID

		AD addr = DatatypesFactory.eINSTANCE.createAD(); // Created Address and put it
		patientRole.getAddrs().add(addr); // in the PatientRole!!!
		addr.getUses().add(PostalAddressUse.HP);
		addr.addStreetAddressLine(readDemog.getAddress());
		addr.addCity(readDemog.getCity());
		addr.addState(readDemog.getState());
		addr.addCountry(readDemog.getCountry());
		addr.addPostalCode(readDemog.getZip());

		TEL telecom = DatatypesFactory.eINSTANCE.createTEL();
		telecom.setValue("tel:" + readDemog.getPhone());
		telecom.getUses().add(TelecommunicationAddressUse.HP);
		patientRole.getTelecoms().add(telecom); // Telecom value!!!

		Patient patient = CDAFactory.eINSTANCE.createPatient(); // Then Creates Patient!
		patientRole.setPatient(patient);

		PN name = DatatypesFactory.eINSTANCE.createPN(); // Set First and last names!

		name.addGiven(readDemog.getFirst_name());
		name.addFamily(readDemog.getLast_name());
		name.addSuffix(readDemog.getMi()); // and M.I.!
		patient.getNames().add(name);
		// set name

		CE administrativeGenderCode = DatatypesFactory.eINSTANCE.createCE();
		patient.setAdministrativeGenderCode(administrativeGenderCode);
		administrativeGenderCode.setCode(readDemog.getGender());
		administrativeGenderCode.setCodeSystem("2.16.840.1.113883.5.1");

		TS birthTime = DatatypesFactory.eINSTANCE.createTS(); // Birthday and added
		patient.setBirthTime(birthTime);
		birthTime.setValue(readDemog.getDob());

		CE raceCode = DatatypesFactory.eINSTANCE.createCE();
		raceCode.setCode("raceCode");
		raceCode.setCodeSystem("2.16.840.1.113883.6.238");
		raceCode.setCodeSystemName("Race and Ethnicity - CDC");
		raceCode.setDisplayName(readDemog.getRace());
		patient.setRaceCode(raceCode);

		CE ethnicGroupCode = DatatypesFactory.eINSTANCE.createCE();
		ethnicGroupCode.setCode("2106-3");
		ethnicGroupCode.setCodeSystem("2.16.840.1.113883.6.238");
		ethnicGroupCode.setCodeSystemName("Race and Ethnicity - CDC");
		ethnicGroupCode.setDisplayName(readDemog.getEthinic_group());
		patient.setEthnicGroupCode(ethnicGroupCode);

		// Author!!!

		Author author = CDAFactory.eINSTANCE.createAuthor();
		doc.getAuthors().add(author);

		String authorTimeS = new String("000000+0500");
		authorTimeS = readAuthor.getTime() + authorTimeS;
		TS authorTime = DatatypesFactory.eINSTANCE.createTS(authorTimeS);

		author.setTime(authorTime);

		AssignedAuthor assignedAuthor = CDAFactory.eINSTANCE.createAssignedAuthor();

		II authorId = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.4.6", "111111");
		assignedAuthor.getIds().add(authorId);

		AD authorAddr = DatatypesFactory.eINSTANCE.createAD(); // Created Address and put it
		assignedAuthor.getAddrs().add(authorAddr); // in the PatientRole!!!
		authorAddr.addStreetAddressLine(readAuthor.getAddress());
		authorAddr.addCity(readAuthor.getCity());
		authorAddr.addState(readAuthor.getState());
		authorAddr.addPostalCode(readAuthor.getZip());
		authorAddr.addCountry(readAuthor.getCountry());

		TEL telecomAuthor = DatatypesFactory.eINSTANCE.createTEL();
		String telecomAuthorS = new String("tel:");
		telecomAuthorS = telecomAuthorS + readAuthor.getPhone();
		telecomAuthor.setValue(telecomAuthorS);
		telecomAuthor.getUses().add(TelecommunicationAddressUse.WP);
		assignedAuthor.getTelecoms().add(telecomAuthor); // Telecom value!!!

		Person person = CDAFactory.eINSTANCE.createPerson();
		PN nameAuthor = DatatypesFactory.eINSTANCE.createPN();
		nameAuthor.addPrefix(readAuthor.getPrefix());
		nameAuthor.addGiven(readAuthor.getFirst_name());
		nameAuthor.addFamily(readAuthor.getLast_name());
		person.getNames().add(nameAuthor);

		assignedAuthor.setAssignedPerson(person);

		author.setAssignedAuthor(assignedAuthor);

		// Custodian Starts

		Custodian custodian = CDAFactory.eINSTANCE.createCustodian();
		AssignedCustodian assignedCustodian = CDAFactory.eINSTANCE.createAssignedCustodian();
		CustodianOrganization custodianOrganization = CDAFactory.eINSTANCE.createCustodianOrganization();

		II custodianId = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.4.6", "99999999");
		custodianOrganization.getIds().add(custodianId);

		ON custodianName = DatatypesFactory.eINSTANCE.createON();
		custodianName.addText(readCustodian.getName());
		custodianOrganization.setName(custodianName);

		TEL telecomCustodian = DatatypesFactory.eINSTANCE.createTEL();
		String telecomCustodianS = new String("tel:");
		telecomCustodianS = telecomCustodianS + readCustodian.getPhone();
		telecomCustodian.setValue(telecomCustodianS);
		telecomCustodian.getUses().add(TelecommunicationAddressUse.WP);
		custodianOrganization.setTelecom(telecomCustodian);

		AD custodianAddr = DatatypesFactory.eINSTANCE.createAD(); // Created Address and put it
		custodianOrganization.setAddr(custodianAddr);
		custodianAddr.addStreetAddressLine(readCustodian.getAddress());
		custodianAddr.addCity(readCustodian.getCity());
		custodianAddr.addState(readCustodian.getState());
		custodianAddr.addPostalCode(readCustodian.getZip());
		custodianAddr.addCountry(readCustodian.getCountry());

		assignedCustodian.setRepresentedCustodianOrganization(custodianOrganization);
		custodian.setAssignedCustodian(assignedCustodian);

		doc.setCustodian(custodian);

		// ComponentOf!!!
		Component1 componentOf = CDAFactory.eINSTANCE.createComponent1();
		EncompassingEncounter encompassingEncounter = CDAFactory.eINSTANCE.createEncompassingEncounter();
		doc.setComponentOf(componentOf);
		componentOf.setEncompassingEncounter(encompassingEncounter);

		II id_compOf = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.3.2853", "1");
		encompassingEncounter.getIds().add(id_compOf);

		CE code_compOf = DatatypesFactory.eINSTANCE.createCE(
			"99213", "2.16.840.1.113883.6.12", "CPT-4", "Evaluation and Management");
		encompassingEncounter.setCode(code_compOf);

		IVL_TS effectiveTime_compOf = DatatypesFactory.eINSTANCE.createIVL_TS();

		effectiveTime_compOf.setValue(readDoc.getDate());

		encompassingEncounter.setEffectiveTime(effectiveTime_compOf);

		ResponsibleParty responsibleParty = CDAFactory.eINSTANCE.createResponsibleParty();
		encompassingEncounter.setResponsibleParty(responsibleParty);

		AssignedEntity assignedEntity = CDAFactory.eINSTANCE.createAssignedEntity();
		responsibleParty.setAssignedEntity(assignedEntity);

		II id_compOfRespEnt = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.4.6");
		assignedEntity.getIds().add(id_compOfRespEnt);

		Person assignedPerson_compOfResEnt = CDAFactory.eINSTANCE.createPerson();
		assignedEntity.setAssignedPerson(assignedPerson_compOfResEnt);

		PN name_compOfResEnt = DatatypesFactory.eINSTANCE.createPN(); // Set First and last names!

		name_compOfResEnt.addGiven(readAuthor.getFirst_name());
		name_compOfResEnt.addFamily(readAuthor.getLast_name());
		name_compOfResEnt.addPrefix(readAuthor.getPrefix());
		assignedPerson_compOfResEnt.getNames().add(name_compOfResEnt);

		DocumentationOf documentationOf = CDAFactory.eINSTANCE.createDocumentationOf();
		documentationOf.setTypeCode(ActRelationshipType.DOC);

		ServiceEvent serviceEvent = CDAFactory.eINSTANCE.createServiceEvent();
		documentationOf.setServiceEvent(serviceEvent);

		CE code_docOfSer = DatatypesFactory.eINSTANCE.createCE(
			"99213", "2.16.840.1.113883.6.12", "CPT-4", "Evaluation and Management");
		serviceEvent.setCode(code_docOfSer);

		IVL_TS effectiveTime_docOfSer = DatatypesFactory.eINSTANCE.createIVL_TS();

		IVXB_TS effectiveTimeLow_docOfSer = DatatypesFactory.eINSTANCE.createIVXB_TS(); // There is no known time
		effectiveTimeLow_docOfSer.setValue(readDoc.getDate());
		effectiveTime_docOfSer.setLow(effectiveTimeLow_docOfSer);

		serviceEvent.setEffectiveTime(effectiveTime_docOfSer);

		Performer1 performer_docOf = CDAFactory.eINSTANCE.createPerformer1();
		serviceEvent.getPerformers().add(performer_docOf);
		performer_docOf.setTypeCode(x_ServiceEventPerformer.PRF);

		CE code_perfDocOf = DatatypesFactory.eINSTANCE.createCE(
			readDoc.getCode(), "2.16.840.1.113883.12.443", "Provider Role", readDoc.getProvider());
		performer_docOf.setFunctionCode(code_perfDocOf);

		AssignedEntity assignedEntity_docOf = CDAFactory.eINSTANCE.createAssignedEntity();
		performer_docOf.setAssignedEntity(assignedEntity_docOf);

		II id_assEntDocOf = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.3.2853", "PP-1");
		assignedEntity_docOf.getIds().add(id_assEntDocOf);
		// ID and Extension!!!

		CE code_assEntDocOf = DatatypesFactory.eINSTANCE.createCE(
			"208D00000X", "2.16.840.1.113883.6.101", "NUCC", "General Practice");
		assignedEntity_docOf.setCode(code_assEntDocOf);

		AD addr_assEntDocOf = DatatypesFactory.eINSTANCE.createAD();
		assignedEntity_docOf.getAddrs().add(addr_assEntDocOf);

		addr_assEntDocOf.addStreetAddressLine(readDoc.getAddress());
		addr_assEntDocOf.addCity(readDoc.getCity());
		addr_assEntDocOf.addState(readDoc.getState());
		addr_assEntDocOf.addPostalCode(readDoc.getZip());
		addr_assEntDocOf.addCountry(readDoc.getCountry());

		TEL telecom_assEntDocOf = DatatypesFactory.eINSTANCE.createTEL();
		String telecomText = new String("tel:");
		telecomText = telecomText + readDoc.getPhone();
		telecom_assEntDocOf.setValue(telecomText);
		telecom_assEntDocOf.getUses().add(TelecommunicationAddressUse.WP);
		assignedEntity_docOf.getTelecoms().add(telecom_assEntDocOf); // Telecom value!!!

		Person assignedPerson_docOf = CDAFactory.eINSTANCE.createPerson();
		assignedEntity_docOf.setAssignedPerson(assignedPerson_docOf);

		PN name_assPerDocOf = DatatypesFactory.eINSTANCE.createPN();
		assignedPerson_docOf.getNames().add(name_assPerDocOf);
		name_assPerDocOf.addPrefix(readDoc.getPrefix());
		name_assPerDocOf.addGiven(readDoc.getFirst_name());
		name_assPerDocOf.addFamily(readDoc.getLast_name());

		doc.getDocumentationOfs().add(documentationOf);

		// **********************************************************************************************************
		// Allergy
		// **********************************************************************************************************

		Section section_Allergy = CDAFactory.eINSTANCE.createSection();
		doc.addSection(section_Allergy);

		II templateId_alg = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.2.6.1");
		section_Allergy.getTemplateIds().add(templateId_alg);

		CE code_alg = DatatypesFactory.eINSTANCE.createCE("48765-2", "2.16.840.1.113883.6.1");
		section_Allergy.setCode(code_alg);
		// set Code

		ST title_alg = DatatypesFactory.eINSTANCE.createST("ALLERGIES, ADVERSE REACTIONS, ALERTS");
		section_Allergy.setTitle(title_alg);

		if (readAlg.getSubs().get(1).startsWith("No known Allergies")) {

			section_Allergy.createStrucDocText(readAlg.getSubs().get(1));

			Entry entry_alg1 = CDAFactory.eINSTANCE.createEntry();
			entry_alg1.setTypeCode(x_ActRelationshipEntry.DRIV);

			Act act_alg1 = CDAFactory.eINSTANCE.createAct();

			act_alg1.setClassCode(x_ActClassDocumentEntryAct.ACT);
			act_alg1.setMoodCode(x_DocumentActMood.EVN);

			entry_alg1.setAct(act_alg1);

			II templateId_entAlg = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.30");

			act_alg1.getTemplateIds().add(templateId_entAlg);

			II algId = DatatypesFactory.eINSTANCE.createII(UUID.randomUUID().toString());
			act_alg1.getIds().add(algId);

			CD code_entAlg = DatatypesFactory.eINSTANCE.createCD(
				"48765-2", "2.16.840.1.113883.6.1", "LOINC", "Allergies, adverse reactions, alerts");
			act_alg1.setCode(code_entAlg);

			CS statusCode_entAlg = DatatypesFactory.eINSTANCE.createCS("completed");
			act_alg1.setStatusCode(statusCode_entAlg);

			IVL_TS effectiveTime_entAlg = DatatypesFactory.eINSTANCE.createIVL_TS();

			IVXB_TS effectiveTimeHigh_entAlg = DatatypesFactory.eINSTANCE.createIVXB_TS(); // There is no known time
			effectiveTimeHigh_entAlg.setNullFlavor(NullFlavor.UNK);

			effectiveTime_entAlg.setHigh(effectiveTimeHigh_entAlg);

			act_alg1.setEffectiveTime(effectiveTime_entAlg);

			EntryRelationship entryRelationship_entAlgAct = CDAFactory.eINSTANCE.createEntryRelationship();
			act_alg1.getEntryRelationships().add(entryRelationship_entAlgAct);
			entryRelationship_entAlgAct.setTypeCode(x_ActRelationshipEntryRelationship.SUBJ);

			Observation obs_AlgEntActEnt = CDAFactory.eINSTANCE.createObservation();
			entryRelationship_entAlgAct.setObservation(obs_AlgEntActEnt);

			obs_AlgEntActEnt.setClassCode(ActClassObservation.OBS);
			obs_AlgEntActEnt.setMoodCode(x_ActMoodDocumentObservation.EVN);
			obs_AlgEntActEnt.setNullFlavor(NullFlavor.NI);

			II templateId_entAlgEntObs = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.7");

			obs_AlgEntActEnt.getTemplateIds().add(templateId_entAlgEntObs);

			II Id_entAlgEntObs = DatatypesFactory.eINSTANCE.createII(UUID.randomUUID().toString());
			obs_AlgEntActEnt.getIds().add(Id_entAlgEntObs);

			CD code_entAlgEntObs = DatatypesFactory.eINSTANCE.createCD();
			code_entAlgEntObs.setCode("ASSERTION");
			code_entAlgEntObs.setCodeSystem("2.16.840.1.113883.5.4");
			obs_AlgEntActEnt.setCode(code_entAlgEntObs);

			IVL_TS effectiveTime_entAlgEntObs = DatatypesFactory.eINSTANCE.createIVL_TS();

			IVXB_TS effectiveTimeHigh_entAlgEntObs = DatatypesFactory.eINSTANCE.createIVXB_TS(); // There is no known time
			effectiveTimeHigh_entAlgEntObs.setNullFlavor(NullFlavor.UNK);

			effectiveTime_entAlgEntObs.setHigh(effectiveTimeHigh_entAlgEntObs);

			obs_AlgEntActEnt.setEffectiveTime(effectiveTime_entAlgEntObs);

			CS statusCode_entAlgEntObs = DatatypesFactory.eINSTANCE.createCS("completed");
			obs_AlgEntActEnt.setStatusCode(statusCode_entAlgEntObs);

			ANY value_AlgEntActEntObs1 = DatatypesFactory.eINSTANCE.createCD(
				"419199007", "2.16.840.1.113883.6.96", "SNOMED CT", "Allergy to substance (disorder)");

			obs_AlgEntActEnt.getValues().add(value_AlgEntActEntObs1);

			section_Allergy.getEntries().add(entry_alg1);

		} else {

			StringBuffer buffer_alg1 = new StringBuffer();

			buffer_alg1.append("\n");
			buffer_alg1.append("                <table border=\"1\" width=\"100%\">\n");
			buffer_alg1.append("                     <thead>\n");
			buffer_alg1.append("                          <tr>\n");
			buffer_alg1.append("                               <th>" + readAlg.getSubs().get(0) + "</th>\n");
			buffer_alg1.append("                               <th>" + readAlg.getReaction().get(0) + "</th>\n");
			buffer_alg1.append("                               <th>" + readAlg.getSeverity().get(0) + "</th>\n");
			buffer_alg1.append("                               <th>" + readAlg.getStatus().get(0) + "</th>\n");
			buffer_alg1.append("                          </tr>\n");
			buffer_alg1.append("                     </thead>\n");
			buffer_alg1.append("                     <tbody>\n");

			for (int i = 1; i < readAlg.getLineCount(); i++) {
				buffer_alg1.append("                          <tr>\n");
				buffer_alg1.append("                               <td><content ID=\"substance" + i + "\">" +
						readAlg.getSubs().get(i) + "</content></td>\n");
				buffer_alg1.append("                               <td>" + readAlg.getReaction().get(i) + "</td>\n");
				buffer_alg1.append("                               <td><content ID=\"severity" + i + "\">" +
						readAlg.getSeverity().get(i) + "</content></td>\n");
				buffer_alg1.append("                               <td>" + readAlg.getStatus().get(i) + "</td>\n");
				buffer_alg1.append("                          </tr>\n");
			}
			buffer_alg1.append("                     </tbody>\n");
			buffer_alg1.append("                </table>");

			section_Allergy.createStrucDocText(buffer_alg1.toString());

			for (int i = 1; i < readAlg.getLineCount(); i++) {
				Entry entry_alg1 = CDAFactory.eINSTANCE.createEntry();
				entry_alg1.setTypeCode(x_ActRelationshipEntry.DRIV);

				Act act_alg1 = CDAFactory.eINSTANCE.createAct();

				act_alg1.setClassCode(x_ActClassDocumentEntryAct.ACT);
				act_alg1.setMoodCode(x_DocumentActMood.EVN);

				entry_alg1.setAct(act_alg1);

				II templateId_entAlg = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.30");

				act_alg1.getTemplateIds().add(templateId_entAlg);

				II algId = DatatypesFactory.eINSTANCE.createII(UUID.randomUUID().toString());
				act_alg1.getIds().add(algId);

				CD code_entAlg = DatatypesFactory.eINSTANCE.createCD(
					"48765-2", "2.16.840.1.113883.6.1", "LOINC", "Allergies, adverse reactions, alerts");
				act_alg1.setCode(code_entAlg);

				if (readAlg.getEnd_date().get(i).startsWith("current") ||
						readAlg.getEnd_date().get(i).startsWith("Current")) {
					CS statusCode_entAlg = DatatypesFactory.eINSTANCE.createCS("active");
					act_alg1.setStatusCode(statusCode_entAlg);

					IVL_TS effectiveTime_entAlg = DatatypesFactory.eINSTANCE.createIVL_TS();

					IVXB_TS effectiveTimeLow_entAlg = DatatypesFactory.eINSTANCE.createIVXB_TS();
					effectiveTimeLow_entAlg.setValue(readAlg.getStart_date().get(i));

					effectiveTime_entAlg.setLow(effectiveTimeLow_entAlg);

					act_alg1.setEffectiveTime(effectiveTime_entAlg);

				} else {
					CS statusCode_entAlg = DatatypesFactory.eINSTANCE.createCS("completed");
					act_alg1.setStatusCode(statusCode_entAlg);

					IVL_TS effectiveTime_entAlg = DatatypesFactory.eINSTANCE.createIVL_TS();

					if (!readAlg.getStart_date().get(i).startsWith("UNK") &&
							!readAlg.getStart_date().get(i).startsWith("unk")) {
						IVXB_TS effectiveTimeLow_entAlg = DatatypesFactory.eINSTANCE.createIVXB_TS();
						effectiveTimeLow_entAlg.setValue(readAlg.getStart_date().get(i));
						effectiveTime_entAlg.setLow(effectiveTimeLow_entAlg);
					} else {
						IVXB_TS effectiveTimeLow_entAlg = DatatypesFactory.eINSTANCE.createIVXB_TS();
						effectiveTimeLow_entAlg.setNullFlavor(NullFlavor.UNK);
						effectiveTime_entAlg.setLow(effectiveTimeLow_entAlg);
					}
					if (!readAlg.getEnd_date().get(i).startsWith("current") &&
							!readAlg.getEnd_date().get(i).startsWith("Current")) {
						IVXB_TS effectiveTimeHigh_entAlg = DatatypesFactory.eINSTANCE.createIVXB_TS();
						effectiveTimeHigh_entAlg.setValue(readAlg.getEnd_date().get(i));

						effectiveTime_entAlg.setHigh(effectiveTimeHigh_entAlg);

						act_alg1.setEffectiveTime(effectiveTime_entAlg);
					}
				}

				EntryRelationship entryRelationship_entAlgAct = CDAFactory.eINSTANCE.createEntryRelationship();
				act_alg1.getEntryRelationships().add(entryRelationship_entAlgAct);
				entryRelationship_entAlgAct.setTypeCode(x_ActRelationshipEntryRelationship.SUBJ);

				Observation obs_AlgEntActEnt = CDAFactory.eINSTANCE.createObservation();
				entryRelationship_entAlgAct.setObservation(obs_AlgEntActEnt);

				obs_AlgEntActEnt.setClassCode(ActClassObservation.OBS);
				obs_AlgEntActEnt.setMoodCode(x_ActMoodDocumentObservation.EVN);

				II templateId_entAlgEntObs = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.7");

				// Substance
				//
				obs_AlgEntActEnt.getTemplateIds().add(templateId_entAlgEntObs);

				II Id_entAlgEntObs = DatatypesFactory.eINSTANCE.createII(UUID.randomUUID().toString());
				obs_AlgEntActEnt.getIds().add(Id_entAlgEntObs);

				CD code_entAlgEntObs = DatatypesFactory.eINSTANCE.createCD();
				code_entAlgEntObs.setCode("ASSERTION");
				code_entAlgEntObs.setCodeSystem("2.16.840.1.113883.5.4");
				obs_AlgEntActEnt.setCode(code_entAlgEntObs);

				CS statusCode_entAlgEntObs = DatatypesFactory.eINSTANCE.createCS("completed");
				obs_AlgEntActEnt.setStatusCode(statusCode_entAlgEntObs);

				IVL_TS effectiveTime_entAlgEntObs = DatatypesFactory.eINSTANCE.createIVL_TS();

				if (!readAlg.getStart_date().get(i).startsWith("UNK") &&
						!readAlg.getStart_date().get(i).startsWith("unk")) {

					IVXB_TS effectiveTimeLow_entAlgEntObs = DatatypesFactory.eINSTANCE.createIVXB_TS();
					effectiveTimeLow_entAlgEntObs.setValue(readAlg.getStart_date().get(i));
					effectiveTime_entAlgEntObs.setLow(effectiveTimeLow_entAlgEntObs);
				} else {
					IVXB_TS effectiveTimeLow_entAlgEntObs = DatatypesFactory.eINSTANCE.createIVXB_TS();
					effectiveTimeLow_entAlgEntObs.setNullFlavor(NullFlavor.UNK);
					effectiveTime_entAlgEntObs.setLow(effectiveTimeLow_entAlgEntObs);
				}
				if (!readAlg.getEnd_date().get(i).startsWith("current") &&
						!readAlg.getEnd_date().get(i).startsWith("Current")) {
					IVXB_TS effectiveTimeHigh_entAlgEntObs = DatatypesFactory.eINSTANCE.createIVXB_TS();
					effectiveTimeHigh_entAlgEntObs.setValue(readAlg.getEnd_date().get(i));

					effectiveTime_entAlgEntObs.setHigh(effectiveTimeHigh_entAlgEntObs);

				}
				obs_AlgEntActEnt.setEffectiveTime(effectiveTime_entAlgEntObs);

				CD value_AlgEntActEntObs2 = DatatypesFactory.eINSTANCE.createCD(
					"416098002", "2.16.840.1.113883.6.96", "SNOMED CT", "Drug allergy");

				obs_AlgEntActEnt.getValues().add(value_AlgEntActEntObs2);

				ED entry_algText = DatatypesFactory.eINSTANCE.createED();
				value_AlgEntActEntObs2.setOriginalText(entry_algText);

				TEL entry_algRef = DatatypesFactory.eINSTANCE.createTEL("#reaction" + i);

				entry_algText.setReference(entry_algRef);

				Participant2 participant_alg = CDAFactory.eINSTANCE.createParticipant2();
				obs_AlgEntActEnt.getParticipants().add(participant_alg);

				participant_alg.setTypeCode(ParticipationType.CSM);

				ParticipantRole participantRole_alg = CDAFactory.eINSTANCE.createParticipantRole();
				participant_alg.setParticipantRole(participantRole_alg);
				participantRole_alg.setClassCode(RoleClassRoot.MANU);

				PlayingEntity playingEntity_alg = CDAFactory.eINSTANCE.createPlayingEntity();
				playingEntity_alg.setClassCode(EntityClassRoot.MMAT);
				participantRole_alg.setPlayingEntity(playingEntity_alg);
				//

				CE code_algEntPartEnt = DatatypesFactory.eINSTANCE.createCE(
					readAlg.getSubs_code().get(i), "2.16.840.1.113883.6.88", "RxNorm", readAlg.getSubs().get(i));
				playingEntity_alg.setCode(code_algEntPartEnt);

				ED text_algEntPartEnt = DatatypesFactory.eINSTANCE.createED();
				TEL textRef_algEntPartEnt = DatatypesFactory.eINSTANCE.createTEL("#substance" + i);

				text_algEntPartEnt.setReference(textRef_algEntPartEnt);
				code_algEntPartEnt.setOriginalText(text_algEntPartEnt);

				//
				// Status
				EntryRelationship entryRelationship_entAlgStatusAct = CDAFactory.eINSTANCE.createEntryRelationship();
				obs_AlgEntActEnt.getEntryRelationships().add(entryRelationship_entAlgStatusAct);
				entryRelationship_entAlgStatusAct.setTypeCode(x_ActRelationshipEntryRelationship.SUBJ);
				entryRelationship_entAlgStatusAct.setInversionInd(true);

				Observation obs_entAlgStatusAct = CDAFactory.eINSTANCE.createObservation();
				entryRelationship_entAlgStatusAct.setObservation(obs_entAlgStatusAct);

				obs_entAlgStatusAct.setClassCode(ActClassObservation.OBS);
				obs_entAlgStatusAct.setMoodCode(x_ActMoodDocumentObservation.EVN);

				II templateId_entAlgStatusAct = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.28");
				obs_entAlgStatusAct.getTemplateIds().add(templateId_entAlgStatusAct);

				CD code_entAlgStatusAct = DatatypesFactory.eINSTANCE.createCD(
					"33999-4", "2.16.840.1.113883.6.1", "LOINC", "Status");
				obs_entAlgStatusAct.setCode(code_entAlgStatusAct);

				CS statusCode_entAlgStatusAct = DatatypesFactory.eINSTANCE.createCS("completed");
				obs_entAlgStatusAct.setStatusCode(statusCode_entAlgStatusAct);

				CE value_entAlgStatusAct = DatatypesFactory.eINSTANCE.createCE();
				value_entAlgStatusAct.setCode(readAlg.getStatus_code().get(i));
				value_entAlgStatusAct.setCodeSystem("2.16.840.1.113883.6.96");
				value_entAlgStatusAct.setDisplayName(readAlg.getStatus().get(i));

				obs_entAlgStatusAct.getValues().add(value_entAlgStatusAct);

				//
				// Reaction
				EntryRelationship entryRelationship_entAlgReactionAct = CDAFactory.eINSTANCE.createEntryRelationship();
				obs_AlgEntActEnt.getEntryRelationships().add(entryRelationship_entAlgReactionAct);
				entryRelationship_entAlgReactionAct.setTypeCode(x_ActRelationshipEntryRelationship.MFST);
				entryRelationship_entAlgReactionAct.setInversionInd(true);

				Observation obs_entAlgReactionAct = CDAFactory.eINSTANCE.createObservation();
				entryRelationship_entAlgReactionAct.setObservation(obs_entAlgReactionAct);

				obs_entAlgReactionAct.setClassCode(ActClassObservation.OBS);
				obs_entAlgReactionAct.setMoodCode(x_ActMoodDocumentObservation.EVN);

				II templateId_entAlgReactionAct = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.9");
				obs_entAlgReactionAct.getTemplateIds().add(templateId_entAlgReactionAct);

				II Id_entAlgReactionAct = DatatypesFactory.eINSTANCE.createII(UUID.randomUUID().toString());
				obs_entAlgReactionAct.getIds().add(Id_entAlgReactionAct);

				CD code_entAlgReactionAct = DatatypesFactory.eINSTANCE.createCD();
				code_entAlgReactionAct.setCode("ASSERTION");
				code_entAlgReactionAct.setCodeSystem("2.16.840.1.113883.5.4");
				obs_entAlgReactionAct.setCode(code_entAlgReactionAct);

				CS statusCode_entAlgReactionAct = DatatypesFactory.eINSTANCE.createCS("completed");
				obs_entAlgReactionAct.setStatusCode(statusCode_entAlgReactionAct);

				CD value_entAlgReactionAct = DatatypesFactory.eINSTANCE.createCD(
					readAlg.getReaction_code().get(i), "2.16.840.1.113883.6.96", "SNOMED CT",
					readAlg.getReaction().get(i));
				obs_entAlgReactionAct.getValues().add(value_entAlgReactionAct);
				//
				// Severity
				EntryRelationship entryRelationship_entAlgSeverityAct = CDAFactory.eINSTANCE.createEntryRelationship();
				obs_AlgEntActEnt.getEntryRelationships().add(entryRelationship_entAlgSeverityAct);
				entryRelationship_entAlgSeverityAct.setTypeCode(x_ActRelationshipEntryRelationship.SUBJ);
				entryRelationship_entAlgSeverityAct.setInversionInd(true);

				Observation obs_entAlgSeverityAct = CDAFactory.eINSTANCE.createObservation();
				entryRelationship_entAlgSeverityAct.setObservation(obs_entAlgSeverityAct);

				obs_entAlgSeverityAct.setClassCode(ActClassObservation.OBS);
				obs_entAlgSeverityAct.setMoodCode(x_ActMoodDocumentObservation.EVN);

				II templateId_entAlgSeverityAct = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.8");
				obs_entAlgSeverityAct.getTemplateIds().add(templateId_entAlgSeverityAct);

				CE code_entAlgSeverityAct = DatatypesFactory.eINSTANCE.createCE(
					"SEV", "2.16.840.1.113883.5.4", "HL7ActCode", "Severity Observation");
				obs_entAlgSeverityAct.setCode(code_entAlgSeverityAct);

				ED text_entAlgSeverityAct = DatatypesFactory.eINSTANCE.createED();
				obs_entAlgSeverityAct.setText(text_entAlgSeverityAct);

				TEL textRef_entAlgSeverityAct = DatatypesFactory.eINSTANCE.createTEL("#severity" + i);
				text_entAlgSeverityAct.setReference(textRef_entAlgSeverityAct);

				CS statusCode_entAlgSeverityAct = DatatypesFactory.eINSTANCE.createCS("completed");
				obs_entAlgSeverityAct.setStatusCode(statusCode_entAlgSeverityAct);

				CD value_entAlgSeverityAct = DatatypesFactory.eINSTANCE.createCD(
					readAlg.getSeverity_code().get(i), "2.16.840.1.113883.6.96", "SNOMED CT",
					readAlg.getSeverity().get(i));
				obs_entAlgSeverityAct.getValues().add(value_entAlgSeverityAct);


				section_Allergy.getEntries().add(entry_alg1);
			}
		}

		// **********************************************************************************************************
		// Encounters
		// **********************************************************************************************************

		Section section_Encounters = CDAFactory.eINSTANCE.createSection();
		doc.addSection(section_Encounters);
		// adding Another Section

		II templateId_enc = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.2.22.1");
		section_Encounters.getTemplateIds().add(templateId_enc);

		CE code_enc = DatatypesFactory.eINSTANCE.createCE(
			"46240-8", "2.16.840.1.113883.6.1", "LOINC", "History of encounters");
		section_Encounters.setCode(code_enc);
		// set Code

		ST title_enc = DatatypesFactory.eINSTANCE.createST("Encounters");
		section_Encounters.setTitle(title_enc);

		StringBuffer buffer_enc1 = new StringBuffer();

		buffer_enc1.append("\n");
		buffer_enc1.append("                <table border=\"1\" width=\"100%\">\n");
		buffer_enc1.append("                     <thead>\n");
		buffer_enc1.append("                          <tr>\n");
		buffer_enc1.append("                               <th>" + readEcns.getVisitDate().get(0) + "</th>\n");
		buffer_enc1.append("                               <th>" + readEcns.getChiefComplaint().get(0) + "</th>\n");
		buffer_enc1.append("                               <th>" + readEcns.getDiagnosis().get(0) + "</th>\n");
		buffer_enc1.append("                               <th>" + readEcns.getIcd9().get(0) + "</th>\n");
		buffer_enc1.append("                               <th>" + readEcns.getHeight().get(0) + "</th>\n");
		buffer_enc1.append("                               <th>" + readEcns.getWeight().get(0) + "</th>\n");
		buffer_enc1.append("                               <th>" + readEcns.getPulse().get(0) + "</th>\n");
		buffer_enc1.append("                               <th>" + readEcns.getRespiration().get(0) + "</th>\n");
		buffer_enc1.append("                               <th>" + readEcns.getBloodPressure().get(0) + "</th>\n");
		buffer_enc1.append("                               <th>" + readEcns.getNotes().get(0) + "</th>\n");
		buffer_enc1.append("                          </tr>\n");
		buffer_enc1.append("                     </thead>\n");

		buffer_enc1.append("                     <tbody>\n");

		for (int i = 1; i < readEcns.getLineCount(); i++) {
			buffer_enc1.append("                          <tr>\n");
			buffer_enc1.append("                               <td>" + "<content ID=\"Encounter" + i + "\"/>" +
					readEcns.getVisitDate().get(i) + "</td>\n");
			buffer_enc1.append("                               <td>" + readEcns.getChiefComplaint().get(i) + "</td>\n");
			buffer_enc1.append("                               <td>" + readEcns.getDiagnosis().get(i) + "</td>\n");
			buffer_enc1.append("                               <td>" + readEcns.getIcd9().get(i) + "</td>\n");
			buffer_enc1.append("                               <td>" + readEcns.getHeight().get(i) + "</td>\n");
			buffer_enc1.append("                               <td>" + readEcns.getWeight().get(i) + "</td>\n");
			buffer_enc1.append("                               <td>" + readEcns.getPulse().get(i) + "</td>\n");
			buffer_enc1.append("                               <td>" + readEcns.getRespiration().get(i) + "</td>\n");
			buffer_enc1.append("                               <td>" + readEcns.getBloodPressure().get(i) + "</td>\n");
			buffer_enc1.append("                               <td>" + readEcns.getNotes().get(i) + "</td>\n");
			buffer_enc1.append("                          </tr>\n");
		}
		buffer_enc1.append("                     </tbody>\n");
		buffer_enc1.append("                </table>");

		section_Encounters.createStrucDocText(buffer_enc1.toString());

		// ***************** Entries of Encounters **************
		for (int i = 1; i < readEcns.getLineCount(); i++) {
			Entry entry_enc1 = CDAFactory.eINSTANCE.createEntry();
			entry_enc1.setTypeCode(x_ActRelationshipEntry.DRIV);

			Encounter enc_ent1 = CDAFactory.eINSTANCE.createEncounter();

			enc_ent1.setClassCode(ActClass.ENC);
			enc_ent1.setMoodCode(x_DocumentEncounterMood.EVN);

			II templateId_enc1 = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.49");

			enc_ent1.getTemplateIds().add(templateId_enc1);
			II id_enc1 = DatatypesFactory.eINSTANCE.createII(UUID.randomUUID().toString());

			enc_ent1.getIds().add(id_enc1);
			// ID and Extension!!!

			CD code_enc1 = DatatypesFactory.eINSTANCE.createCD(); // This part is fixed for this version.-----------------
			code_enc1.setCode("99241");
			code_enc1.setCodeSystem("2.16.840.1.113883.6.12");
			code_enc1.setCodeSystemName("CPT");
			code_enc1.setDisplayName("Annual Physical Exam");
			code_enc1.setCodeSystemVersion("4");

			ED entry_enc1Text = DatatypesFactory.eINSTANCE.createED();

			entry_enc1Text.addText("Checkup Examination");
			code_enc1.setOriginalText(entry_enc1Text);
			enc_ent1.setCode(code_enc1);

			TEL entry_enc1Ref = DatatypesFactory.eINSTANCE.createTEL("#Encounter" + i);

			entry_enc1Text.setReference(entry_enc1Ref);

			CS StatusCode_enc1 = DatatypesFactory.eINSTANCE.createCS("completed"); // This is fixed for this version.
			enc_ent1.setStatusCode(StatusCode_enc1);

			IVL_TS effectiveTime_enc1 = DatatypesFactory.eINSTANCE.createIVL_TS();
			effectiveTime_enc1.setValue(readEcns.getVisitDate().get(i));
			enc_ent1.setEffectiveTime(effectiveTime_enc1);
			entry_enc1.setEncounter(enc_ent1);

			section_Encounters.getEntries().add(entry_enc1);
		}

		// **********************************************************************************************************
		// Immunizations
		// **********************************************************************************************************

		Section section_Immu = CDAFactory.eINSTANCE.createSection();
		doc.addSection(section_Immu);
		// adding Another Section

		II templateId_Immu = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.2.2.1");
		section_Immu.getTemplateIds().add(templateId_Immu);

		CE code_Immu = DatatypesFactory.eINSTANCE.createCE(
			"11369-6", "2.16.840.1.113883.6.1", "LOINC", "History of immunizations");
		section_Immu.setCode(code_Immu);
		// set Code

		ST title_Immu = DatatypesFactory.eINSTANCE.createST("IMMUNIZATIONS");
		section_Immu.setTitle(title_Immu);

		StringBuffer buffer_immu = new StringBuffer();

		buffer_immu.append("\n");
		buffer_immu.append("                <table border=\"1\" width=\"100%\">\n");
		buffer_immu.append("                     <thead>\n");
		buffer_immu.append("                          <tr>\n");
		buffer_immu.append("                               <th>" + readImmu.getVaccine().get(0) + "</th>\n");
		buffer_immu.append("                               <th>" + readImmu.getDate().get(0) + "</th>\n");
		buffer_immu.append("                               <th>" + readImmu.getStatus().get(0) + "</th>\n");
		buffer_immu.append("                          </tr>\n");
		buffer_immu.append("                     </thead>\n");
		buffer_immu.append("                     <tbody>\n");

		for (int i = 1; i < readImmu.getLineCount(); i++) {
			buffer_immu.append("                          <tr>\n");
			buffer_immu.append("                               <td><content ID=\"immun" + i + "\">" +
					readImmu.getVaccine().get(i) + "</content></td>\n");
			buffer_immu.append("                               <td>" + readImmu.getDate().get(i) + "</td>\n");
			buffer_immu.append("                               <td>" + readImmu.getStatus().get(i) + "</td>\n");
			buffer_immu.append("                          </tr>\n");
		}

		buffer_immu.append("                     </tbody>\n");
		buffer_immu.append("                </table>");

		section_Immu.createStrucDocText(buffer_immu.toString());

		// ***************** Entries of Immunizations **************

		for (int i = 1; i < readImmu.getLineCount(); i++) {
			Entry entry_immu = CDAFactory.eINSTANCE.createEntry();
			section_Immu.getEntries().add(entry_immu);
			entry_immu.setTypeCode(x_ActRelationshipEntry.DRIV);

			SubstanceAdministration subsAdm_immuEnt = CDAFactory.eINSTANCE.createSubstanceAdministration();
			entry_immu.setSubstanceAdministration(subsAdm_immuEnt);

			subsAdm_immuEnt.setClassCode(ActClass.SBADM);
			subsAdm_immuEnt.setMoodCode(x_DocumentSubstanceMood.EVN);
			subsAdm_immuEnt.setNegationInd(false);

			II templateId_immu = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.52");
			subsAdm_immuEnt.getTemplateIds().add(templateId_immu);

			II id_immu = DatatypesFactory.eINSTANCE.createII(UUID.randomUUID().toString());
			subsAdm_immuEnt.getIds().add(id_immu);
			// ID and Extension!!!
			ED text_immu = DatatypesFactory.eINSTANCE.createED();
			TEL ref_immuText = DatatypesFactory.eINSTANCE.createTEL();
			ref_immuText.setValue("#immun" + i);
			text_immu.setReference(ref_immuText);
			subsAdm_immuEnt.setText(text_immu);

			CS StatusCode_immu = DatatypesFactory.eINSTANCE.createCS(readImmu.getStatus().get(i)); // This is fixed for this version.
			subsAdm_immuEnt.setStatusCode(StatusCode_immu);

			IVL_TS effectiveTime_immu = DatatypesFactory.eINSTANCE.createIVL_TS();
			effectiveTime_immu.setValue(readImmu.getDate().get(i));
			subsAdm_immuEnt.getEffectiveTimes().add(effectiveTime_immu);

			IVL_PQ doseQunt_immuEnt = DatatypesFactory.eINSTANCE.createIVL_PQ();
			BigDecimal bigDec_immuEntDoseQty = new BigDecimal(readImmu.getDose_Qty().get(i));

			doseQunt_immuEnt.setValue(bigDec_immuEntDoseQty);
			doseQunt_immuEnt.setUnit(readImmu.getUnit().get(i));
			subsAdm_immuEnt.setDoseQuantity(doseQunt_immuEnt);

			Consumable consumable_immu = CDAFactory.eINSTANCE.createConsumable();
			ManufacturedProduct manufacturedProc_immu = CDAFactory.eINSTANCE.createManufacturedProduct();
			manufacturedProc_immu.setClassCode(RoleClassManufacturedProduct.MANU);
			consumable_immu.setManufacturedProduct(manufacturedProc_immu);
			subsAdm_immuEnt.setConsumable(consumable_immu);

			II templateId_immuManu = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.54"); // GOOD
			manufacturedProc_immu.getTemplateIds().add(templateId_immuManu);

			Material material = CDAFactory.eINSTANCE.createMaterial();
			manufacturedProc_immu.setManufacturedMaterial(material);

			CE code_material = DatatypesFactory.eINSTANCE.createCE(
				readImmu.getVaccine_code().get(i), "2.16.840.1.113883.12.292", "CVX", readImmu.getVaccine().get(i));
			material.setCode(code_material);
			ED orginalText_material = DatatypesFactory.eINSTANCE.createED(readImmu.getVaccine().get(i));
			code_material.setOriginalText(orginalText_material);

			if (!readImmu.getProduct().get(i).startsWith("None") && !readImmu.getProduct().get(i).startsWith("none")) {
				CD translation_material = DatatypesFactory.eINSTANCE.createCD(
					readImmu.getProduct_code().get(i), "2.16.840.1.113883.12.292", "CVX", readImmu.getProduct().get(i));
				code_material.getTranslations().add(translation_material);
			}
		}

		// **********************************************************************************************************
		// Medications
		// **********************************************************************************************************

		Section section_Medications = CDAFactory.eINSTANCE.createSection();
		doc.addSection(section_Medications);
		// adding Another Section

		II templateId_med = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.2.1.1"); // GOOD
		section_Medications.getTemplateIds().add(templateId_med);

		CE code_med = DatatypesFactory.eINSTANCE.createCE(
			"10160-0", "2.16.840.1.113883.6.1", "LOINC", "HISTORY OF MEDICATION USE"); // GOOD
		section_Medications.setCode(code_med);
		// set Code

		ST title_med = DatatypesFactory.eINSTANCE.createST("Medications");
		section_Medications.setTitle(title_med);

		StringBuffer buffer_med = new StringBuffer();

		buffer_med.append("\n");
		buffer_med.append("                <table border=\"1\" width=\"100%\">\n");
		buffer_med.append("                     <thead>\n");
		buffer_med.append("                          <tr>\n");
		buffer_med.append("                               <th>" + readMed.getStart_date().get(0) + "</th>\n");
		buffer_med.append("                               <th>" + readMed.getStop_date().get(0) + "</th>\n");
		buffer_med.append("                               <th>" + readMed.getDrug().get(0) + "</th>\n");
		buffer_med.append("                               <th>" + readMed.getRoute().get(0) + "</th>\n");
		buffer_med.append("                               <th>" + readMed.getType().get(0) + "</th>\n");
		buffer_med.append("                               <th>" + readMed.getSig().get(0) + "</th>\n");
		buffer_med.append("                          </tr>\n");
		buffer_med.append("                     </thead>\n");
		buffer_med.append("                     <tbody>\n");

		for (int i = 1; i < readMed.getLineCount(); i++) {
			buffer_med.append("                          <tr>\n");
			buffer_med.append("                               <td>" + readMed.getStart_date().get(i) + "</td>\n");
			buffer_med.append("                               <td>" + readMed.getStop_date().get(i) + "</td>\n");
			buffer_med.append("                               <td><content ID=\"Medication" + i + "\">" +
					readMed.getDrug().get(i) + "</content></td>\n");
			buffer_med.append("                               <td>" + readMed.getRoute().get(i) + "</td>\n");
			buffer_med.append("                               <td>" + readMed.getType().get(i) + "</td>\n");
			buffer_med.append("                               <td>" + readMed.getSig().get(i) + "</td>\n");
			buffer_med.append("                          </tr>\n");
		}

		buffer_med.append("                     </tbody>\n");
		buffer_med.append("                </table>");

		section_Medications.createStrucDocText(buffer_med.toString());

		// ***************** Entries of Medications **************

		for (int i = 1; i < readMed.getLineCount(); i++) {
			Entry entry_medEntry1 = CDAFactory.eINSTANCE.createEntry();
			entry_medEntry1.setTypeCode(x_ActRelationshipEntry.DRIV);

			SubstanceAdministration subAd_medEntsubAd1 = CDAFactory.eINSTANCE.createSubstanceAdministration();
			subAd_medEntsubAd1.setClassCode(ActClass.SBADM);
			subAd_medEntsubAd1.setMoodCode(x_DocumentSubstanceMood.EVN);

			II templateId_medEntsubAd1 = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.16"); // GOOD
			subAd_medEntsubAd1.getTemplateIds().add(templateId_medEntsubAd1);

			II id_medEntsubAd1 = DatatypesFactory.eINSTANCE.createII(UUID.randomUUID().toString());
			subAd_medEntsubAd1.getIds().add(id_medEntsubAd1);

			ED text_medEntsubAd1 = DatatypesFactory.eINSTANCE.createED();
			subAd_medEntsubAd1.setText(text_medEntsubAd1);

			TEL ref_medEntsubAd1 = DatatypesFactory.eINSTANCE.createTEL("#Medication" + i);
			text_medEntsubAd1.setReference(ref_medEntsubAd1);

			text_medEntsubAd1.addText(readMed.getDrug().get(i) + ", " + readMed.getSig().get(i));

			CS statusCode_medEntsubAd1 = DatatypesFactory.eINSTANCE.createCS(readMed.getStatus().get(i)); // This if fixed or get rid of?
			subAd_medEntsubAd1.setStatusCode(statusCode_medEntsubAd1);

			IVL_TS effectiveTime_medEntsubAd1 = DatatypesFactory.eINSTANCE.createIVL_TS();

			IVXB_TS effectiveTimeLow_medEntsubAd1 = DatatypesFactory.eINSTANCE.createIVXB_TS(); // There is no known time
			effectiveTimeLow_medEntsubAd1.setValue(readMed.getStart_date().get(i));

			effectiveTime_medEntsubAd1.setLow(effectiveTimeLow_medEntsubAd1);

			if (!readMed.getStatus().get(i).startsWith("active") && !readMed.getStatus().get(i).startsWith("Active")) {
				IVXB_TS effectiveTimeHigh_medEntsubAd1 = DatatypesFactory.eINSTANCE.createIVXB_TS();
				effectiveTimeHigh_medEntsubAd1.setValue(readMed.getStop_date().get(i));

				effectiveTime_medEntsubAd1.setHigh(effectiveTimeHigh_medEntsubAd1);
			}

			subAd_medEntsubAd1.getEffectiveTimes().add(effectiveTime_medEntsubAd1);

			PIVL_TS effectiveTime_medEntsubAd1_1 = DatatypesFactory.eINSTANCE.createPIVL_TS();
			effectiveTime_medEntsubAd1_1.setInstitutionSpecified(true);
			effectiveTime_medEntsubAd1_1.setOperator(SetOperator.A);

			CE routeCode_medEntsubAd1 = DatatypesFactory.eINSTANCE.createCE(
				readMed.getRoute_code().get(i), "2.16.840.1.113883.3.26.1.1", "NCI Thesaurus",
				readMed.getRoute().get(i)); // This is fixed.
			subAd_medEntsubAd1.setRouteCode(routeCode_medEntsubAd1);

			IVL_PQ doseQunt_medEntsubAd1 = DatatypesFactory.eINSTANCE.createIVL_PQ();
			BigDecimal bigDec_medEntsubAdDoseQty = new BigDecimal(readMed.getDoes_qty().get(i));

			doseQunt_medEntsubAd1.setValue(bigDec_medEntsubAdDoseQty);
			subAd_medEntsubAd1.setDoseQuantity(doseQunt_medEntsubAd1);

			IVL_PQ rateQunt_medEntsubAd1 = DatatypesFactory.eINSTANCE.createIVL_PQ();
			BigDecimal bigDec_medEntsubAdRateQty = new BigDecimal(readMed.getRate_qty().get(i));
			rateQunt_medEntsubAd1.setValue(bigDec_medEntsubAdRateQty);
			rateQunt_medEntsubAd1.setUnit(readMed.getRate_qtyUnit().get(i));
			subAd_medEntsubAd1.setRateQuantity(rateQunt_medEntsubAd1);

			CE AdmUnitCode_medEntsubAd1 = DatatypesFactory.eINSTANCE.createCE(
				readMed.getType_code().get(i), "2.16.840.1.113883.3.26.1.1", "NCI Thesaurus", readMed.getType().get(i));
			subAd_medEntsubAd1.setAdministrationUnitCode(AdmUnitCode_medEntsubAd1);

			Consumable consumable_medEntsubAd1 = CDAFactory.eINSTANCE.createConsumable();
			ManufacturedProduct manufacturedProduct_medEntsubAdCons1 = CDAFactory.eINSTANCE.createManufacturedProduct();
			manufacturedProduct_medEntsubAdCons1.setClassCode(RoleClassManufacturedProduct.MANU);

			II templateId_medEntsubAdConsManu1 = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.23"); // ???? Can This be here
			manufacturedProduct_medEntsubAdCons1.getTemplateIds().add(templateId_medEntsubAdConsManu1);

			Material material_medEntsubAdConsManu1 = CDAFactory.eINSTANCE.createMaterial();
			CE code_medEntsubAdConsManu1 = DatatypesFactory.eINSTANCE.createCE(
				readMed.getRxNorm().get(i), "2.16.840.1.113883.6.88", "RxNorm", readMed.getDrug().get(i));

			ED orginalText_medEntsubAdConsManu1 = DatatypesFactory.eINSTANCE.createED();
			TEL reference_medEntsubAdConsManuOrgText1 = DatatypesFactory.eINSTANCE.createTEL("#medication" + i);
			orginalText_medEntsubAdConsManu1.setReference(reference_medEntsubAdConsManuOrgText1);
			code_medEntsubAdConsManu1.setOriginalText(orginalText_medEntsubAdConsManu1);

			material_medEntsubAdConsManu1.setCode(code_medEntsubAdConsManu1);
			manufacturedProduct_medEntsubAdCons1.setManufacturedMaterial(material_medEntsubAdConsManu1);

			consumable_medEntsubAd1.setManufacturedProduct(manufacturedProduct_medEntsubAdCons1);

			subAd_medEntsubAd1.setConsumable(consumable_medEntsubAd1);

			entry_medEntry1.setSubstanceAdministration(subAd_medEntsubAd1);
			// organizer -> entry
			section_Medications.getEntries().add(entry_medEntry1);
		}

		// **********************************************************************************************************
		// Problem Lists
		// **********************************************************************************************************

		Section section_ProblemLists = CDAFactory.eINSTANCE.createSection();
		doc.addSection(section_ProblemLists);

		II templateId_prob = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.2.5.1");
		section_ProblemLists.getTemplateIds().add(templateId_prob);

		CE code_prob = DatatypesFactory.eINSTANCE.createCE("11450-4", "2.16.840.1.113883.6.1", "LOINC", "PROBLEM LIST");
		section_ProblemLists.setCode(code_prob);

		ST title_prob = DatatypesFactory.eINSTANCE.createST("PROBLEMS");
		section_ProblemLists.setTitle(title_prob);

		StringBuffer buffer_prob = new StringBuffer();

		buffer_prob.append("<content ID=\"problems\"/>\n");
		buffer_prob.append("                 <list listType=\"ordered\">\n");

		for (int i = 1; i < readProbs.getLineCount(); i++) {
			buffer_prob.append("                     <item>");
			buffer_prob.append("<content ID=\"problem" + i + "\">" + readProbs.getProblems().get(i) + "</content>");
			buffer_prob.append("</item>\n");
		}

		buffer_prob.append("                 </list>\n");

		section_ProblemLists.createStrucDocText(buffer_prob.toString());

		// ***************** Entries of Problem Lists **************

		for (int i = 1; i < readProbs.getLineCount(); i++) {
			Entry entry_probEntry1 = CDAFactory.eINSTANCE.createEntry();
			entry_probEntry1.setTypeCode(x_ActRelationshipEntry.DRIV);

			Act act_probAct1 = CDAFactory.eINSTANCE.createAct();
			act_probAct1.setClassCode(x_ActClassDocumentEntryAct.ACT);
			act_probAct1.setMoodCode(x_DocumentActMood.EVN);

			II templateId_probEntAct1 = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.3");
			act_probAct1.getTemplateIds().add(templateId_probEntAct1);

			II id_probAct1 = DatatypesFactory.eINSTANCE.createII(UUID.randomUUID().toString());
			act_probAct1.getIds().add(id_probAct1);

			CD code_probEntAct1 = DatatypesFactory.eINSTANCE.createCD();
			code_probEntAct1.setCode("CONC");
			code_probEntAct1.setCodeSystem("2.16.840.1.113883.5.6");
			code_probEntAct1.setDisplayName("Concern");
			act_probAct1.setCode(code_probEntAct1);

			// Add Status???
			CS statusCode_probEntAct1 = DatatypesFactory.eINSTANCE.createCS(readProbs.getStatus().get(i));
			act_probAct1.setStatusCode(statusCode_probEntAct1);

			IVL_TS effectiveTime_probEntAct1 = DatatypesFactory.eINSTANCE.createIVL_TS();
			IVXB_TS lowTime_probEntAct1 = DatatypesFactory.eINSTANCE.createIVXB_TS();
			lowTime_probEntAct1.setValue(readProbs.getFrom().get(i));

			if (!readProbs.getStatus().get(i).startsWith("active") &&
					!readProbs.getStatus().get(i).startsWith("Active")) {
				IVXB_TS highTime_probEntAct1 = DatatypesFactory.eINSTANCE.createIVXB_TS();
				highTime_probEntAct1.setValue(readProbs.getTo().get(i));
				effectiveTime_probEntAct1.setHigh(highTime_probEntAct1);
			}

			effectiveTime_probEntAct1.setLow(lowTime_probEntAct1);
			act_probAct1.setEffectiveTime(effectiveTime_probEntAct1);

			EntryRelationship act_entRel1 = CDAFactory.eINSTANCE.createEntryRelationship();
			act_entRel1.setTypeCode(x_ActRelationshipEntryRelationship.SUBJ);

			Observation obs_probEntActEntObs1 = CDAFactory.eINSTANCE.createObservation();
			obs_probEntActEntObs1.setClassCode(ActClassObservation.OBS);
			obs_probEntActEntObs1.setMoodCode(x_ActMoodDocumentObservation.EVN);

			II templateId_probEntActEntObs1 = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.4");
			obs_probEntActEntObs1.getTemplateIds().add(templateId_probEntActEntObs1);

			II id_probEntActEntObs1 = DatatypesFactory.eINSTANCE.createII(UUID.randomUUID().toString());
			obs_probEntActEntObs1.getIds().add(id_probEntActEntObs1);

			CD code_probEntActEntObs1 = DatatypesFactory.eINSTANCE.createCD();
			code_probEntActEntObs1.setCode("409586006");
			code_probEntActEntObs1.setCodeSystem("2.16.840.1.113883.6.96"); // <ProbObsCodeSystem>??
			code_probEntActEntObs1.setDisplayName("Complaint"); // <ProbObsCodeDisplayName>

			obs_probEntActEntObs1.setCode(code_probEntActEntObs1);

			ED text_probEntActEntObs1 = DatatypesFactory.eINSTANCE.createED();
			obs_probEntActEntObs1.setText(text_probEntActEntObs1);

			TEL ref_probEntActEntObs1 = DatatypesFactory.eINSTANCE.createTEL("#problem" + i);
			text_probEntActEntObs1.setReference(ref_probEntActEntObs1);

			CS StatusCode_probEntActEntObs1 = DatatypesFactory.eINSTANCE.createCS("completed");
			obs_probEntActEntObs1.setStatusCode(StatusCode_probEntActEntObs1);

			// Value
			ANY value_probEntActEntObs1 = DatatypesFactory.eINSTANCE.createCD(
				readProbs.getSnomed().get(i), "2.16.840.1.113883.6.96", "SNOMED CT", readProbs.getProblems().get(i));

			obs_probEntActEntObs1.getValues().add(value_probEntActEntObs1);

			act_entRel1.setObservation(obs_probEntActEntObs1);
			act_probAct1.getEntryRelationships().add(act_entRel1);

			entry_probEntry1.setAct(act_probAct1);
			section_ProblemLists.getEntries().add(entry_probEntry1);
		}

		// **********************************************************************************************************
		// Results
		// **********************************************************************************************************

		Section section_LabTests = CDAFactory.eINSTANCE.createSection();
		doc.addSection(section_LabTests);
		// adding Another Section

		II templateId_labT = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.2.3.1");
		section_LabTests.getTemplateIds().add(templateId_labT);

		CE code_labT = DatatypesFactory.eINSTANCE.createCE("30954-2", "2.16.840.1.113883.6.1", "LOINC", "RESULTS");
		section_LabTests.setCode(code_labT);
		// set Code

		ST title_labT = DatatypesFactory.eINSTANCE.createST("RESULTS");
		section_LabTests.setTitle(title_labT);

		StringBuffer buffer_labT = new StringBuffer();

		buffer_labT.append("\n");
		buffer_labT.append("                <table border=\"1\" width=\"100%\">\n");
		buffer_labT.append("                     <thead>\n");
		buffer_labT.append("                          <tr>\n");
		buffer_labT.append("                               <th>" + readLabT.getVisitDate().get(0) + "</th>\n");
		buffer_labT.append("                               <th>" + readLabT.getTest().get(0) + "</th>\n");
		buffer_labT.append("                               <th>" + readLabT.getLoinc().get(0) + "</th>\n");
		buffer_labT.append("                               <th>" + readLabT.getValue().get(0) + "</th>\n");
		buffer_labT.append("                               <th>" + readLabT.getUnit().get(0) + "</th>\n");
		buffer_labT.append("                               <th>" + readLabT.getShortName().get(0) + "</th>\n");
		buffer_labT.append("                          </tr>\n");
		buffer_labT.append("                     </thead>\n");
		buffer_labT.append("                     <tbody>\n");

		for (int i = 1; i < readLabT.getLineCount(); i++) {

			buffer_labT.append("                          <tr>\n");
			buffer_labT.append("                               <td><content ID=\"result" + i + "\"/>" +
					readLabT.getVisitDate().get(i) + "</td>\n");
			buffer_labT.append("                               <td>" + readLabT.getTest().get(i) + "</td>\n");
			buffer_labT.append("                               <td>" + readLabT.getLoinc().get(i) + "</td>\n");
			buffer_labT.append("                               <td>" + readLabT.getValue().get(i) + "</td>\n");
			buffer_labT.append("                               <td>" + readLabT.getUnit().get(i) + "</td>\n");
			buffer_labT.append("                               <td>" + readLabT.getShortName().get(i) + "</td>\n");
			buffer_labT.append("                          </tr>\n");
		}

		buffer_labT.append("                     </tbody>\n");
		buffer_labT.append("                </table>");

		section_LabTests.createStrucDocText(buffer_labT.toString());

		// ***************** Entries of Results **************

		for (int j = 1; j < readLabT.getLineCount(); j++) {
			Entry entry_labtEntry1 = CDAFactory.eINSTANCE.createEntry();
			entry_labtEntry1.setTypeCode(x_ActRelationshipEntry.DRIV);

			Organizer org_labtEntOrg1 = CDAFactory.eINSTANCE.createOrganizer();
			org_labtEntOrg1.setClassCode(x_ActClassDocumentEntryOrganizer.BATTERY);
			org_labtEntOrg1.setMoodCode(ActMood.EVN);

			II templateId_labtEntOrg1 = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.1");
			org_labtEntOrg1.getTemplateIds().add(templateId_labtEntOrg1);

			II id_labtOrg1 = DatatypesFactory.eINSTANCE.createII(UUID.randomUUID().toString());
			org_labtEntOrg1.getIds().add(id_labtOrg1);

			CD code_labtEntOrg1 = DatatypesFactory.eINSTANCE.createCD();
			code_labtEntOrg1.setCode(readLabT.getLoinc().get(j));
			code_labtEntOrg1.setCodeSystem("2.16.840.1.113883.6.1");
			code_labtEntOrg1.setCodeSystemName("LOINC");
			code_labtEntOrg1.setDisplayName(readLabT.getTest().get(j));
			org_labtEntOrg1.setCode(code_labtEntOrg1);

			CS statusCode_labtEntOrg1 = DatatypesFactory.eINSTANCE.createCS("completed");
			org_labtEntOrg1.setStatusCode(statusCode_labtEntOrg1);

			Component4 component4_labtEntOrg1 = CDAFactory.eINSTANCE.createComponent4();
			org_labtEntOrg1.getComponents().add(component4_labtEntOrg1);
			// Organizer - Component4 - Observation!

			Observation obs_labtEntOrgComObs1 = CDAFactory.eINSTANCE.createObservation();
			obs_labtEntOrgComObs1.setClassCode(ActClassObservation.OBS);
			obs_labtEntOrgComObs1.setMoodCode(x_ActMoodDocumentObservation.EVN);

			II templateId_labtEntOrgComObs1 = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.2");
			obs_labtEntOrgComObs1.getTemplateIds().add(templateId_labtEntOrgComObs1);

			II id_labtEntOrgComObs1 = DatatypesFactory.eINSTANCE.createII(UUID.randomUUID().toString());
			obs_labtEntOrgComObs1.getIds().add(id_labtEntOrgComObs1);
			// ID and Extension!!!

			CE code_labtEntOrgComObs1 = DatatypesFactory.eINSTANCE.createCE();
			// type?
			code_labtEntOrgComObs1.setCode(readLabT.getLoinc().get(j));
			code_labtEntOrgComObs1.setCodeSystem("2.16.840.1.113883.6.1");
			code_labtEntOrgComObs1.setCodeSystemName("LOINC");
			code_labtEntOrgComObs1.setDisplayName(readLabT.getTest().get(j));

			obs_labtEntOrgComObs1.setCode(code_labtEntOrgComObs1);

			ED text_labtEntOrgComObs1 = DatatypesFactory.eINSTANCE.createED();
			obs_labtEntOrgComObs1.setText(text_labtEntOrgComObs1);

			TEL ref_labtEntOrgComObs1 = DatatypesFactory.eINSTANCE.createTEL("#result" + j);
			text_labtEntOrgComObs1.setReference(ref_labtEntOrgComObs1);

			CS StatusCode_labtEntOrgComObs1 = DatatypesFactory.eINSTANCE.createCS("completed");
			obs_labtEntOrgComObs1.setStatusCode(StatusCode_labtEntOrgComObs1);

			IVL_TS effectiveTime_labtEntOrgComObs1 = DatatypesFactory.eINSTANCE.createIVL_TS();
			effectiveTime_labtEntOrgComObs1.setValue(readLabT.getVisitDate().get(j));
			obs_labtEntOrgComObs1.setEffectiveTime(effectiveTime_labtEntOrgComObs1);

			if (readLabT.getUnit().get(j).startsWith("text") || readLabT.getUnit().get(j).startsWith("Text")) {
				ANY value_labtEntOrgComObs1 = DatatypesFactory.eINSTANCE.createST(readLabT.getValue().get(j));
				obs_labtEntOrgComObs1.getValues().add(value_labtEntOrgComObs1);
			} else {
				ANY value_labtEntOrgComObs1 = DatatypesFactory.eINSTANCE.createPQ(
					Double.parseDouble(readLabT.getValue().get(j)), readLabT.getUnit().get(j));

				
				obs_labtEntOrgComObs1.getValues().add(value_labtEntOrgComObs1);
			}
			component4_labtEntOrg1.setObservation(obs_labtEntOrgComObs1);
			

			entry_labtEntry1.setOrganizer(org_labtEntOrg1);
			// organizer -> entry
			section_LabTests.getEntries().add(entry_labtEntry1);
			// entry -> section
		}

		// **********************************************************************************************************
		// Social History
		// **********************************************************************************************************

		Section section_History = CDAFactory.eINSTANCE.createSection();
		doc.addSection(section_History);

		II templateId_History = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.2.17");
		section_History.getTemplateIds().add(templateId_History);

		CE code_History = DatatypesFactory.eINSTANCE.createCE(
			"29762-2", "2.16.840.1.113883.6.1", "LOINC", "Social History");
		section_History.setCode(code_History);
		// set Code

		ST title_History = DatatypesFactory.eINSTANCE.createST("social History");
		section_History.setTitle(title_History);

		StringBuffer buffer = new StringBuffer();

		buffer.append("\n");
		buffer.append("                <table border=\"1\" width=\"100%\">\n");
		buffer.append("                     <thead>\n");
		buffer.append("                          <tr>\n");
		buffer.append("                               <th>" + readHist.getHistory().get(0) + "</th>\n");
		buffer.append("                               <th>" + readHist.getFrom().get(0) + "</th>\n");
		buffer.append("                               <th>" + readHist.getTo().get(0) + "</th>\n");
		buffer.append("                          </tr>\n");
		buffer.append("                     </thead>\n");
		buffer.append("                     <tbody>\n");
		for (int i = 1; i < readHist.getLineCount(); i++) {
			buffer.append("                          <tr>\n");
			buffer.append("                               <td><content ID=\"soc" + i + "\"/>" +
					readHist.getHistory().get(1) + "</td>\n");
			buffer.append("                               <td>" + readHist.getFrom().get(i) + "</td>\n");
			buffer.append("                               <td>" + readHist.getTo().get(i) + "</td>\n");
			buffer.append("                          </tr>\n");
		}
		buffer.append("                     </tbody>\n");
		buffer.append("                </table>");

		section_History.createStrucDocText(buffer.toString());

		// ***************** Entries for History **************
		for (int i = 1; i < readHist.getLineCount(); i++) {
			Entry entry1 = CDAFactory.eINSTANCE.createEntry();
			entry1.setTypeCode(x_ActRelationshipEntry.DRIV);

			Observation obs1 = CDAFactory.eINSTANCE.createObservation();
			obs1.setClassCode(ActClassObservation.OBS);
			obs1.setMoodCode(x_ActMoodDocumentObservation.EVN);

			II templateId_obs1 = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.22.4.78");
			obs1.getTemplateIds().add(templateId_obs1);

			II id_obs1 = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.19", "123456789");
			obs1.getIds().add(id_obs1);
			// ID and Extension!!!

			CD code_obs1 = DatatypesFactory.eINSTANCE.createCD();
			code_obs1.setCode("ASSERTION");
			code_obs1.setCodeSystem("2.16.840.1.113883.5.4");
			

			obs1.setCode(code_obs1);

			CS StatusCode_obs1 = DatatypesFactory.eINSTANCE.createCS(readHist.getStatus().get(i));
			obs1.setStatusCode(StatusCode_obs1);

			IVL_TS effectiveTime_obs1 = DatatypesFactory.eINSTANCE.createIVL_TS();

			IVXB_TS effectiveTime_obs1Low = DatatypesFactory.eINSTANCE.createIVXB_TS();
			effectiveTime_obs1Low.setValue(readHist.getFrom().get(i));
			effectiveTime_obs1.setLow(effectiveTime_obs1Low);

			if (!readHist.getStatus().get(i).startsWith("active") && !readHist.getStatus().get(i).startsWith("Active")) {
				IVXB_TS effectiveTime_obs1High = DatatypesFactory.eINSTANCE.createIVXB_TS();
				effectiveTime_obs1High.setValue(readHist.getTo().get(i));
				effectiveTime_obs1.setHigh(effectiveTime_obs1High);

			}

			obs1.setEffectiveTime(effectiveTime_obs1);

			CD valueObs = DatatypesFactory.eINSTANCE.createCD();
			valueObs.setCode("8517006");
			valueObs.setDisplayName(readHist.getHistory().get(i));
			valueObs.setCodeSystem("2.16.840.1.113883.6.96");
			obs1.getValues().add(valueObs);

			entry1.setObservation(obs1);

			section_History.getEntries().add(entry1);
		}

		// **********************************************************************************************************
		// Social History
		// **********************************************************************************************************

		Section section_vitalSigns = CDAFactory.eINSTANCE.createSection();
		doc.addSection(section_vitalSigns);

		II templateId_vitalSigns = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.2.4.1");
		section_vitalSigns.getTemplateIds().add(templateId_vitalSigns);

		CE code_vitalSigns = DatatypesFactory.eINSTANCE.createCE(
			"8716-3", "2.16.840.1.113883.6.1", "LOINC", "VITAL SIGNS");
		section_vitalSigns.setCode(code_vitalSigns);
		// set Code

		ST title_vitalSigns = DatatypesFactory.eINSTANCE.createST("VITAL SIGNS");
		section_vitalSigns.setTitle(title_vitalSigns);

		StringBuffer buffer_vitalSigns = new StringBuffer();

		buffer_vitalSigns.append("\n");
		buffer_vitalSigns.append("                <table border=\"1\" width=\"100%\">\n");
		buffer_vitalSigns.append("                     <thead>\n");
		buffer_vitalSigns.append("                          <tr>\n");
		buffer_vitalSigns.append("                               <th>" + readVitals.getDate().get(0) + "</th>\n");
		buffer_vitalSigns.append("                               <th>" + readVitals.getHeight().get(0) + "</th>\n");
		buffer_vitalSigns.append("                               <th>" + readVitals.getWeight().get(0) + "</th>\n");
		buffer_vitalSigns.append("                               <th>" + readVitals.getBloodPressure().get(0) +
				"</th>\n");
		buffer_vitalSigns.append("                          </tr>\n");
		buffer_vitalSigns.append("                     </thead>\n");
		buffer_vitalSigns.append("                     <tbody>\n");

		int lineNum = readVitals.getLineCount();
		for (int i = 1; i < lineNum; i++) {
			buffer_vitalSigns.append("                          <tr>\n");

			buffer_vitalSigns.append("                               <td>" + readVitals.getDate().get(i) + "</td>\n");
			buffer_vitalSigns.append("                               <td><content ID=\"vit" + i + "\">" +
					readVitals.getHeight().get(i) + " " + readVitals.getHeight_unit().get(i) + "</content></td>\n");
			buffer_vitalSigns.append("                               <td><content ID=\"vit" + (lineNum - 1 + i) +
					"\">" + readVitals.getWeight().get(i) + " " + readVitals.getWeight_unit().get(i) +
					"</content></td>\n");
			buffer_vitalSigns.append("                               <td><content ID=\"vit" + (lineNum * 2 - 2 + i) +
					"\">" + readVitals.getBloodPressure().get(i) + " " + readVitals.getBloodPressure_unit().get(i) +
					"</content></td>\n");
			buffer_vitalSigns.append("                          </tr>\n");
		}
		buffer_vitalSigns.append("                     </tbody>\n");
		buffer_vitalSigns.append("                </table>");

		section_vitalSigns.createStrucDocText(buffer_vitalSigns.toString());

		// ***************** Entries for Vital Signs **************

		for (int i = 1; i < readVitals.getLineCount(); i++) {
			Entry entry_vitals = CDAFactory.eINSTANCE.createEntry();
			entry_vitals.setTypeCode(x_ActRelationshipEntry.DRIV);

			Organizer org_vitals = CDAFactory.eINSTANCE.createOrganizer();
			org_vitals.setClassCode(x_ActClassDocumentEntryOrganizer.CLUSTER);
			org_vitals.setMoodCode(ActMood.EVN);
			entry_vitals.setOrganizer(org_vitals);

			II templateId_vitalOrg1 = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.26");
			org_vitals.getTemplateIds().add(templateId_vitalOrg1);

			II id_vitalOrg1 = DatatypesFactory.eINSTANCE.createII(UUID.randomUUID().toString());
			org_vitals.getIds().add(id_vitalOrg1);
			// ID and Extension!!!

			CD code_vitalOrg1 = DatatypesFactory.eINSTANCE.createCD(
				"46680005", "2.16.840.1.113883.6.96", "SNOMED -CT", "Vital signs");
			org_vitals.setCode(code_vitalOrg1);

			CS StatusCode_vitalOrg1 = DatatypesFactory.eINSTANCE.createCS("completed");
			org_vitals.setStatusCode(StatusCode_vitalOrg1);

			IVL_TS effectiveTime_vitalOrg1 = DatatypesFactory.eINSTANCE.createIVL_TS();

			effectiveTime_vitalOrg1.setValue(readVitals.getDate().get(i));
			org_vitals.setEffectiveTime(effectiveTime_vitalOrg1);

			// Height
			//
			//
			Component4 comp_vitalOrg = CDAFactory.eINSTANCE.createComponent4();
			org_vitals.getComponents().add(comp_vitalOrg);

			Observation obs_vitalOrgObs = CDAFactory.eINSTANCE.createObservation();
			comp_vitalOrg.setObservation(obs_vitalOrgObs);
			obs_vitalOrgObs.setClassCode(ActClassObservation.OBS);
			obs_vitalOrgObs.setMoodCode(x_ActMoodDocumentObservation.EVN);

			II templateId_vitalOrgObs = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.27");
			obs_vitalOrgObs.getTemplateIds().add(templateId_vitalOrgObs);

			II id_vitalOrgObs = DatatypesFactory.eINSTANCE.createII(UUID.randomUUID().toString());
			obs_vitalOrgObs.getIds().add(id_vitalOrgObs);

			CD code_vitalOrgObs = DatatypesFactory.eINSTANCE.createCD(
				"8302-2", "2.16.840.1.113883.6.1", "LOINC", "Height");
			obs_vitalOrgObs.setCode(code_vitalOrgObs);

			ED code_vitalOrgObsText = DatatypesFactory.eINSTANCE.createED();
			TEL code_vitalOrgObsTextRef = DatatypesFactory.eINSTANCE.createTEL("#vit" + (((i - 1) * 3) + 1));
			code_vitalOrgObsText.setReference(code_vitalOrgObsTextRef);
			obs_vitalOrgObs.setText(code_vitalOrgObsText);

			CS StatusCode_vitalOrgObs = DatatypesFactory.eINSTANCE.createCS("completed");
			obs_vitalOrgObs.setStatusCode(StatusCode_vitalOrgObs);

			IVL_TS effectiveTime_vitalOrgObs = DatatypesFactory.eINSTANCE.createIVL_TS();
			effectiveTime_vitalOrgObs.setValue(readVitals.getDate().get(i));

			obs_vitalOrgObs.setEffectiveTime(effectiveTime_vitalOrgObs);
			ANY value_vitalOrgObs = DatatypesFactory.eINSTANCE.createPQ(
				Double.parseDouble(readVitals.getHeight().get(i)), readVitals.getHeight_unit().get(i).substring(0, 2));
			obs_vitalOrgObs.getValues().add(value_vitalOrgObs);

			CE interpretationCode = DatatypesFactory.eINSTANCE.createCE("N", "2.16.840.1.113883.5.83");
			obs_vitalOrgObs.getInterpretationCodes().add(interpretationCode);

			// Weight
			//
			//
			Component4 comp_vitalOrg2 = CDAFactory.eINSTANCE.createComponent4();
			org_vitals.getComponents().add(comp_vitalOrg2);

			Observation obs_vitalOrgObs2 = CDAFactory.eINSTANCE.createObservation();
			comp_vitalOrg2.setObservation(obs_vitalOrgObs2);
			obs_vitalOrgObs2.setClassCode(ActClassObservation.OBS);
			obs_vitalOrgObs2.setMoodCode(x_ActMoodDocumentObservation.EVN);

			II templateId_vitalOrgObs2 = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.27");
			obs_vitalOrgObs2.getTemplateIds().add(templateId_vitalOrgObs2);

			II id_vitalOrgObs2 = DatatypesFactory.eINSTANCE.createII(UUID.randomUUID().toString());
			obs_vitalOrgObs2.getIds().add(id_vitalOrgObs2);

			CD code_vitalOrgObs2 = DatatypesFactory.eINSTANCE.createCD(
				"3141-9", "2.16.840.1.113883.6.1", "LOINC", "Patient Body Weight - Measured");
			obs_vitalOrgObs2.setCode(code_vitalOrgObs2);

			ED code_vitalOrgObsText2 = DatatypesFactory.eINSTANCE.createED();
			TEL code_vitalOrgObsTextRef2 = DatatypesFactory.eINSTANCE.createTEL("#vit" + ((i - 1) * 3 + 2));
			code_vitalOrgObsText2.setReference(code_vitalOrgObsTextRef2);
			obs_vitalOrgObs2.setText(code_vitalOrgObsText2);

			CS StatusCode_vitalOrgObs2 = DatatypesFactory.eINSTANCE.createCS("completed");
			obs_vitalOrgObs2.setStatusCode(StatusCode_vitalOrgObs2);

			IVL_TS effectiveTime_vitalOrgObs2 = DatatypesFactory.eINSTANCE.createIVL_TS();
			effectiveTime_vitalOrgObs2.setValue(readVitals.getDate().get(i));

			obs_vitalOrgObs2.setEffectiveTime(effectiveTime_vitalOrgObs2);

			ANY value_vitalOrgObs2 = DatatypesFactory.eINSTANCE.createPQ(
				Double.parseDouble(readVitals.getWeight().get(i)), readVitals.getWeight_unit().get(i));
			obs_vitalOrgObs2.getValues().add(value_vitalOrgObs2);

			CE interpretationCode2 = DatatypesFactory.eINSTANCE.createCE("N", "2.16.840.1.113883.5.83");
			obs_vitalOrgObs2.getInterpretationCodes().add(interpretationCode2);

			// Blood Pressure
			//
			//
			Component4 comp_vitalOrg3 = CDAFactory.eINSTANCE.createComponent4();
			org_vitals.getComponents().add(comp_vitalOrg3);

			Observation obs_vitalOrgObs3 = CDAFactory.eINSTANCE.createObservation();
			comp_vitalOrg3.setObservation(obs_vitalOrgObs3);
			obs_vitalOrgObs3.setClassCode(ActClassObservation.OBS);
			obs_vitalOrgObs3.setMoodCode(x_ActMoodDocumentObservation.EVN);

			II templateId_vitalOrgObs3 = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.10.20.22.4.27");
			obs_vitalOrgObs3.getTemplateIds().add(templateId_vitalOrgObs3);

			II id_vitalOrgObs3 = DatatypesFactory.eINSTANCE.createII(UUID.randomUUID().toString());
			obs_vitalOrgObs3.getIds().add(id_vitalOrgObs3);

			CD code_vitalOrgObs3 = DatatypesFactory.eINSTANCE.createCD(
				"8480-6", "2.16.840.1.113883.6.1", "LOINC", "Intravascular Systolic");
			obs_vitalOrgObs3.setCode(code_vitalOrgObs3);

			ED code_vitalOrgObsText3 = DatatypesFactory.eINSTANCE.createED();
			TEL code_vitalOrgObsTextRef3 = DatatypesFactory.eINSTANCE.createTEL("#vit" + ((i - 1) * 3 + 3));
			code_vitalOrgObsText3.setReference(code_vitalOrgObsTextRef3);
			obs_vitalOrgObs3.setText(code_vitalOrgObsText3);

			CS StatusCode_vitalOrgObs3 = DatatypesFactory.eINSTANCE.createCS("completed");
			obs_vitalOrgObs3.setStatusCode(StatusCode_vitalOrgObs3);

			IVL_TS effectiveTime_vitalOrgObs3 = DatatypesFactory.eINSTANCE.createIVL_TS();
			effectiveTime_vitalOrgObs3.setValue(readVitals.getDate().get(i));

			obs_vitalOrgObs3.setEffectiveTime(effectiveTime_vitalOrgObs3);

			int temp_index = readVitals.getBloodPressure().get(i).indexOf("/");

			ANY value_vitalOrgObs3 = DatatypesFactory.eINSTANCE.createPQ(
				Double.parseDouble(readVitals.getBloodPressure().get(i).substring(0, temp_index)),
				readVitals.getBloodPressure_unit().get(i));
			obs_vitalOrgObs3.getValues().add(value_vitalOrgObs3);

			CE interpretationCode3 = DatatypesFactory.eINSTANCE.createCE("N", "2.16.840.1.113883.5.83");
			obs_vitalOrgObs3.getInterpretationCodes().add(interpretationCode3);

			section_vitalSigns.getEntries().add(entry_vitals);

		}
		//

		CDAUtil.save(doc, System.out);
		System.out.println(); // adds extra line

	}
}
