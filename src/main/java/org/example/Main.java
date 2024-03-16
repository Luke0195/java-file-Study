package org.example;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.example.entities.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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
            hssfWorkbook.close();
            FileInputStream entrada = new FileInputStream(file);
            HSSFWorkbook hssfWorkbook1 = new HSSFWorkbook(entrada);
            HSSFSheet spreadSheet = hssfWorkbook1.getSheetAt(0);
            Iterator<Row> lines = spreadSheet.rowIterator();
            Person person = new Person();
            while(lines != null){
                Row line = lines.next();
                Iterator<Cell> cells = line.cellIterator();
                while(cells.hasNext()){
                    Cell cell = cells.next();
                    switch(cell.getColumnIndex()){
                        case 0:
                            person.setName(cell.getStringCellValue());
                            break;
                        case 1:
                            person.setEmail(cell.getStringCellValue());
                            break;
                        case 2:
                            person.setBirthDate(cell.getStringCellValue());
                    }
                }
            }
            System.out.println(person);
            System.out.println("Planilha criada");
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}