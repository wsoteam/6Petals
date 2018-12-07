package onepic.bkgcom.com.rtreningexercises.TestFill;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import onepic.bkgcom.com.rtreningexercises.Conf;
import onepic.bkgcom.com.rtreningexercises.POJOs.AllPartOfBody;
import onepic.bkgcom.com.rtreningexercises.POJOs.AllWholeArticles;
import onepic.bkgcom.com.rtreningexercises.POJOs.ArrayOfTiles;
import onepic.bkgcom.com.rtreningexercises.POJOs.Article;
import onepic.bkgcom.com.rtreningexercises.POJOs.Ex;
import onepic.bkgcom.com.rtreningexercises.POJOs.ExGroups;
import onepic.bkgcom.com.rtreningexercises.POJOs.GlobalObject;
import onepic.bkgcom.com.rtreningexercises.POJOs.PartOfBody;
import onepic.bkgcom.com.rtreningexercises.POJOs.Programm;
import onepic.bkgcom.com.rtreningexercises.POJOs.Tile;
import onepic.bkgcom.com.rtreningexercises.POJOs.Training;
import onepic.bkgcom.com.rtreningexercises.POJOs.WholeArticle;

public class WorkWithFB {

    public static void fill() {

        Tile tile = new Tile("title", "url_of_image");
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(tile);
        ArrayOfTiles arrayOfTiles = new ArrayOfTiles("title", tiles);
        ArrayList<ArrayOfTiles> arrayOfTilesArrayList = new ArrayList<>();
        arrayOfTilesArrayList.add(arrayOfTiles);
        Training training = new Training("title", "text", "url_of_image", arrayOfTilesArrayList);
        ArrayList<Training> trainingArrayList = new ArrayList<>();
        trainingArrayList.add(training);

        Programm programm = new Programm("title1", trainingArrayList, "img_of_url");
        Programm sec_programm = new Programm("title2", trainingArrayList, "img_of_url");
        Programm third_programm = new Programm("title3", trainingArrayList, "img_of_url");
        ArrayList<Programm> programmArrayList = new ArrayList<>();
        programmArrayList.add(programm);
        programmArrayList.add(sec_programm);
        programmArrayList.add(third_programm);


        Ex ex = new Ex("title", "basic_muscle", "additional_muscle"
                , "complexity", "img_url", "for_man", "for_woman"
                , "detail", "main_chips", "url_of_logo");
        ArrayList<Ex> exArrayList = new ArrayList<>();
        exArrayList.add(ex);
        ExGroups exGroups = new ExGroups("name", exArrayList);
        ArrayList<ExGroups> exGroupsArrayList = new ArrayList<>();
        exGroupsArrayList.add(exGroups);
        exGroupsArrayList.add(exGroups);
        exGroupsArrayList.add(exGroups);
        PartOfBody partOfBody = new PartOfBody("name", "url_of_image", exGroupsArrayList);
        ArrayList<PartOfBody> partOfBodyArrayList = new ArrayList<>();
        partOfBodyArrayList.add(partOfBody);
        AllPartOfBody allPartOfBody = new AllPartOfBody("name", "url_of_image", partOfBodyArrayList);

        Article article = new Article("text", "img_url");
        List<Article> articleList = new ArrayList<>();
        articleList.add(article);
        WholeArticle wholeArticle = new WholeArticle("title", "img_url", articleList);
        List<WholeArticle> wholeArticles = new ArrayList<>();
        wholeArticles.add(wholeArticle);
        AllWholeArticles allWholeArticles = new AllWholeArticles("name", "img_url", wholeArticles);

        GlobalObject globalObject = new GlobalObject("name", programmArrayList, allPartOfBody, allWholeArticles);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Conf.NAME_OF_ENTITY_FOR_DB);

        myRef.setValue(globalObject);


    }
}
