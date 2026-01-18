package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SetMenu;

public class SetMenus extends ArrayList<SetMenu> {

    private final String MENU_TABLE_HEADER =
        "-------------------------------------------------------------------------\n" +
        "| Menu ID| Menu Name           | Price     | Ingredients                  |\n" +
        "-------------------------------------------------------------------------\n";
    private final String MENU_TABLE_FOOTER =
        "-------------------------------------------------------------------------\n";
    private String pathFile;

    //constructor
    public SetMenus() {
        super();
        this.pathFile = "./FeastMenu.csv";
        this.readFromFile();
    }

    public void showAll() {
        System.out.println(MENU_TABLE_HEADER);
        for (SetMenu i : this) {
            System.out.println(i);
        }
        System.out.println(MENU_TABLE_FOOTER);
    }

    public void readFromFile() {
        FileReader fr = null;
        try {
            //1. tạo file
            File f = new File(pathFile);
            //2. ánh xạ
            fr = new FileReader(f);
            //3. tạo bộ đệm để chuyển FileReader
            BufferedReader br = new BufferedReader(fr);
            //4. lặp để đọc dữ liệu
            String tam = "";
            while ((tam = br.readLine()) != null) {
                SetMenu x = textToObject(tam);
                if (x != null) {
                    this.add(x);
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SetMenus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SetMenus.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(SetMenus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
    }

    private SetMenu textToObject(String tam) {
        SetMenu x = new SetMenu();
        String[] temp = tam.split(",");
        try {
            if (temp.length == 4) {
                x.setMenuId(temp[0]);
                x.setMenuName(temp[1]);
                x.setPrice(Double.parseDouble(temp[2]));
                x.setIngredients(temp[3]);
            }
        } catch (Exception e) {
            x = null;
        }
        return x;
    }
}
