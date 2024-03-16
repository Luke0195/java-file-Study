package org.example;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.example.entities.Person;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]){
        try{
            File file = new File("reports/person.xsl");
            if(!file.exists()) file.createNewFile();
            Person person1 = new Person("Lucas", "lucas@mail.com", "1994-05-02");
            Person person2 = new Person("Dieisson", "dieisson@mail.com", "1998-03-20");
            Person person3 = new Person("Raphael", "raphael@mail.com", "1999-08-19");
            List<Person> persons = new ArrayList<>();
            persons.add(person1);
            persons.add(person2);
            persons.add(person3);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(); /** use to write spreadshet */
            HSSFSheet hssfSheet = hssfWorkbook.createSheet("Planilhas de Usuários");

            int numerOfLines = 0;
            for(Person p: persons){
                Row line = hssfSheet.createRow(numerOfLines++);
                line.createCell(0).setCellValue("Id");
                line.createCell(0).setCellValue("Nome");
                line.createCell(0).setCellValue("E-mail");
                line.createCell(0).setCellValue("Data de Nascimento");

                int cell = 0;
                Cell fieldCell = line.createCell(cell++);
                fieldCell.setCellValue(p.getId().toString());

                Cell cellName = line.createCell(cell++);
                cellName.setCellValue(p.getName());

                Cell cellEmail = line.createCell(cell++);
                cellEmail.setCellValue(p.getEmail());

                Cell cellBirthDate = line.createCell(cell++);
                cellBirthDate.setCellValue(p.getBirthDate());
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            hssfWorkbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            System.out.println("Planilha criada");
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}