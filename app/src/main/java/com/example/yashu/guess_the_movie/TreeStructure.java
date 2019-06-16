package com.example.yashu.guess_the_movie;

import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by YASHU on 01-10-2016.
 */
public class TreeStructure {

    public static TreeMap<Character , TreeSet<String>> tm;

    public TreeStructure(InputStream is) throws IOException{

        tm = new TreeMap<>();

        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String line=null;

        while((line = in.readLine()) !=null){

            char c = line.charAt(0);

            if(!tm.containsKey(c)){
                TreeSet<String> ts = new TreeSet<>();
                ts.add(line.toLowerCase().toString());
                tm.put(c,ts);
            }else{
                TreeSet<String> t=tm.get(c);
                t.add(line.toLowerCase());
            }
        }
    }

    public boolean search(String str){

        char c = str.charAt(0);

        if(tm.containsKey(c)){

            TreeSet<String> t = tm.get(c);
            if(t.contains(str.toLowerCase().toString())){
                t.remove(str.toLowerCase().toString());
                if(t.size()==0)
                    tm.remove(c);
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }

    public String computersearch(String str){

        char c = str.charAt(0);
        String temp="";

        if(tm.containsKey(c)){
            TreeSet<String> t = tm.get(c);

               //Iterator<String> itr=t.iterator();
//
          //  while(itr.hasNext()){
             //   String gs = itr.next();

              //  if(str.equals(){
              //      temp = gs;
              //      t.remove(gs.toLowerCase().toString());
               // }
            temp=t.last();
           // Toast.makeText(this.ge, "User Win.", Toast.LENGTH_SHORT).show();
            t.remove(temp);
            if(t.size()==0)
                tm.remove(c);
                return temp;
            }
        else{
            String p="";
            return p;
        }

    }
}
