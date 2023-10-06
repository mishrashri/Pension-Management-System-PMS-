package com.example.pensionerDetail;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Ninjas implements Serializable {
    private String ninjasName;
    private int ninjasAge;
    private int ninjasQuestionSolvedCount;

    public Ninjas(String ninjasName, int ninjasAge, int ninjasQuestionSolvedCount) {
        this.ninjasName = ninjasName;
        this.ninjasAge= ninjasAge;
        this.ninjasQuestionSolvedCount = ninjasQuestionSolvedCount;
    }

	public void setNinjasName(String ninjasName) {
		// TODO Auto-generated method stub
		this.ninjasName = ninjasName;
	}

	public void setNinjasAge(int ninjasAge) {
		// TODO Auto-generated method stub
		this.ninjasAge= ninjasAge;
	}

	public void setNinjasQuestionSolvedCount(int ninjasQuestionSolvedCount) {
		// TODO Auto-generated method stub
		this.ninjasQuestionSolvedCount = ninjasQuestionSolvedCount;
	}
}

public class SerializationProcess{
    public static void main(String[] args) {
        Ninjas ninjas = new Ninjas("Kanak Rana",20,150);

        try {
            FileOutputStream fileOutput = new FileOutputStream("ninjas.ser");
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(ninjas);
            System.out.println("Ninjas! Object is serialized...");
            objectOutput.close();
            fileOutput.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
