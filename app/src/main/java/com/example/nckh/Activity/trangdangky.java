package com.example.nckh.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nckh.R;
import com.example.nckh.SQL.dulieusqllite;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class trangdangky extends AppCompatActivity {

    private EditText edtten,edtmk,edtdc,edtns;
    private EditText _EdtSdt;
    private TextView txt_back;
    private ImageView img;
    private Button btndy;//,btn_address;
    private DatabaseReference databaseReference;
    public FirebaseAuth firebaseAuth;
    public FirebaseDatabase firebaseDatabase;
    public int key01;
    private Cursor cursor;
    private dulieusqllite dl;
    public ProgressDialog progressDialog;
    private String _KeyPassWord = "";
    private EditText _txt_List_Password;
    private Intent intent;
    private String classs1 = "com.mysql.jdbc.Driver";
    private String url1 = "jdbc:mysql://us-cdbr-east-04.cleardb.com:3306/heroku_236e6eebaa8fa47";
    private String un1 = "b37c4a464e7dbf";
    private String password1 = "8a49de4d";
    private Statement st;
    private ResultSet rs;
    private String dcx,dcy;
    private ResultSetMetaData rsmd;
    private Connection conn1 = null;
    private String m1[] = {"a" , "a sweet tooth", "a tall order" , "abnormality", "abnormally" , "absorbent", "absorbing" , "accidentally", "acclaim" , "acid", "acid rain" , "actuality", "actually" , "adjoining",
            "adjourn" , "adulatory", "adult" , "aestivate", "afar" , "against", "agar-agar" , "aha", "ahead" , "airtime", "airwaves" , "all alone", "all along" , "allure",
            "alluring" , "amateurish", "amaze", "amoeba", "amok" , "anarchy", "anathema" , "annals", "annex" ,"anthill", "anthology" , "apathetic",
            "apathetically" , "applied", "apply" , "aquifer", "Arab" , "arid", "aridity" , "artefact", "arterial" , "as sure as", "as the crow flies" , "assail",
            "assailant" , "astounding", "astoundingly" , "at heart", "at home" , "ATM", "atmosphere" , "audacious", "audacity" , "automatically",
            "automation" , "awe-inspiring", "awestruck" , "aye"};
    private String m2[] = {"B" , "backyard", "bacon" , "bale out","baleful" , "banquet","bantam" , "baseball", "baseless" , "batty",
            "bawdy" , "be in short supply", "be in the hot seat" , "be tied up", "be too much for" , "bearer", "bearing" , "bedtime", "bee" , "believe",
            "believe in" , "beset", "beside" , "bicycle", "bid" , "biodiversity", "biofuel" , "bitumen", "bituminous" , "blaze",
            "blazer" , "blonde", "blood" , "blunder", "blunt" , "boldly", "boldness" , "boon", "boor" , "bound for", "bound to" , "brainchild",
            "brainless" , "break off", "break out" , "brief", "briefcase" , "broadcasting", "broaden" , "brute", "brutish" , "bulldozer", "bullet" , "burglary",
            "burgle" , "by all means", "by and by" , "byte"};
    private String m3[] = {"C" , "call centre", "call for" , "candid camera", "candida" , "caper",
            "capillary" , "cardiovascular", "cards" , "carry weight", "carry-all" , "casting vote", "castle" ,"caterpillar", "catfish" , "ceiling",
            "celebrate" , "cephalopod", "cephalothorax" , "champ at the bit", "champagne" , "charm", "charming" , "cheerily", "cheeriness" , "child’s play",
            "childbirth" , "chop", "chop and change" , "churchyard", "churn" , "civil war", "civilian" , "clean out", "clean up" , "clinical",
            "clink" , "cloud", "cloudberry" , "coat of arms", "coating" , "coherent", "coherently" , "colonialist", "colonist" , "come to a sticky end", "come to grief" , "comment",
            "commentary" , "companionable", "companionship" , "complicated", "complication" , "concentrate", "concentrated" , "conditionally", "conditioner" , "confuse",
            "confused" , "conscientiously", "conscientiousness" , "constantly", "constellation" , "contemptuously", "contend" , "contributor", "contrite" , "convoy",
            "convulse" , "corkscrew", "corn" , "corruption", "corset" , "counterfoil", "counterpane" , "covert", "covet" , "crash helmet",
            "crash-land" , "creep up on", "creeper" , "crock", "crockery" , "crown prince", "crown princess" , "cubic centimetre", "cubicle" , "cure", "curfew" , "customer", "customize" , "cytoplasm"};
    private String m4[] = {"D" , "darts", "dash" , "dealer", "dealing" , "deciduous", "decilitre" , "deep-sea", "deepen" , "deftness", "defuse" , "democracy", "democrat" , "dependant",
            "dependence" , "desert", "desert island" , "detached", "detachment" , "dew", "dexterity" , "differentiation", "difficult" , "diner", "ding-dong" , "disagreeable", "disagreeably" , "discontentment",
            "discontinuation" , "dish out", "dish-washing" , "dispatch", "dispatch rider" , "dissidence", "dissident" , "distrustfulness", "disturb" , "DNA", "do" , "dogma", "dogmatic" , "dormant",
            "dormitory" , "downhill", "downhill racing" , "draw", "draw a blank" , "drift", "drifter" , "drum in/into", "drummer" , "dummy", "dump" , "dynamic", "dynamically" , "dyspepsia"};
    private String m5[] = {"E" , "eastward", "eastward(s)" , "ecumenical", "eczema" , "effortless", "effortlessly" , "eldest", "elect" , "eliminate", "elimination" , "embody", "embolism" , "empirical",
            "empirically" , "endearment", "endeavour" , "enlightenment", "enlist" , "entitle", "entitlement" , "equate", "equation" , "ESL", "esophagus" , "evacuate", "evacuation" , "evidence",
            "evident" , "excessively", "excessiveness" , "exhausting", "exhaustion" , "expeditious", "expeditiously" , "expressionless", "expressive" , "extremely", "extremism" , "eyesore"};
    private String m6[] = {"F" , "fair and square", "fair play" , "falsetto", "falsification" , "farming", "farmland" , "faucet", "fault" , "feeling", "feign" , "fetid", "fetish" , "fiftieth",
            "fifty" , "finale", "finalist" , "fireside", "firewood" , "fixation", "fixed" , "flatterer", "flattery" , "floating restaurant", "flock" , "fluorescence", "fluorescent" , "follow suit",
            "follow-up" , "for ever / forever", "for example" , "forehead", "foreign" , "fornication", "forsake" , "fox", "fox terrier" , "freedom fighter", "Freefone" , "(frighten/scare) out of one’s wits",
            "frightened" , "fruit", "fruit salad" , "fungicide", "fungus" , "fuzzy"};
    private String m7[] = {"g" , "gammon", "gander" , "gassiness", "gassy" , "general knowledge", "general store" , "geologically", "geologist" , "get in", "get in on the act" , "ghastliness",
            "ghastly" , "give (someone) the benefit of the doubt", "give (someone) the cold shoulder" , "glasshouse", "glassiness" , "glow-worm", "glower" , "go from bad to worse",
            "go halves with ", "go-karting", "goad ", "good humour", "good luck!" , "graceful", "gracefully" , "graph", "graph paper" , "greenhouse", "greenhouse effect" , "grooved",
            "grope" , "guard of honour", "guarded" , "guppy", "guru" , "gypsy"};
    private String m8[] = {"H" , "half-brother", "half-caste" , "handbag", "handbill" , "Hanukkah", "haphazard" , "harmonious", "harmoniously" , "have (half) a mind to", "have (someone) on a string" , "hawser",
            "hawthorn" , "health", "health centre" , "heave to", "heaven" , "helpfulness", "helping" , "hernia", "hero" , "high", "high and dry" , "hinder", "hindquarters" , "hoarder",
            "hoarding" , "holy", "Holy Thursday" , "honours", "hood" , "horsehair", "horseman" , "house-warming", "houseboat" , "human resources", "humane" , "hunt for", "hunt high and low" , "hyena", "hygiene"};
    private String m9[] = {"I" , "ideologically", "ideology" , "illegality", "illegally" , "immeasurably", "immediate" ,"impeccably", "impede" , "impose", "imposing" ,"in", "in (all) good faith" , "in front (of)", "in full" , "in raptures",
            "in readiness" , "in the nature of", "in the nick of time" , "inadmissible", "inadvertent" , "inclination", "incline" , "incredulous", "increment" , "indirectness", "indiscipline" , "inertia", "inertness" , "infinitive", "infinity" , "ingeniousness", "ingenuity" , "inner tube",
            "innermost" , "inshore", "inside" , "institution", "institutional" , "intent", "intention" , "Internet", "internment" , "introductory", "introspective" , "inviting",
            "invocation" , "irreparable", "irreparably" , "itch", "itchiness" , "ivy"};
    private String m10[] = {"p" , "painful", "painfully" , "panorama", "panoramic" , "parasite", "parasitic" , "participation", "participle" , "pastille", "pastime" , "pattern",
            "patterned" , "peal", "peanut" , "peg", "pejorative" , "pep talk", "pepper" , "perk", "perkily" , "perspiration", "perspire" , "phagocyte", "phalanx" , "photography",
            "photometer" , "pickpocket", "picnic" , "pillar box", "pillion" , "pistachio", "piste" , "plain chocolate", "plain clothes" , "play by ear", "play down" , "plectrum",
            "pledge" , "pm", "PMS" , "poky", "polar" , "polytheism", "polytheistic" , "portable computer", "portent" , "postpone", "postponement" , "pp", "PR" , "preciseness", "precision" , "preposition",
            "prepositional" , "pretty", "pretty much the same" , "primly", "primness" , "proboscis", "procedural" , "progress", "progressive" , "proper noun", "properly" , "protrude",
            "proud", "pt", "PTA" , "pulmonary", "pulp" , "pure-blooded", "pure-bred" , "put aside", "put away" , "PVC", "pygmy" , "python"};
    private String m11[] = {"S" , "sagely", "Sagittarius" , "salver", "same" , "satellite", "satellite dish" , "saw", "sawdust" , "scary", "scathing" , "scissors", "scoff" , "scrawniness",
            "scrawny" , "SE", "sea" , "seclusion", "second" , "sedulously", "see" , "selector", "self" , "sell down the river", "sell off" , "sensational", "sensationally" , "sepulchre", "sequel" , "set (something or someone) on (someone)",
            "set about" , "severe", "severely" , "shake one’s fist at", "shake one’s head" , "shaver", "shavings", "shipbuilder", "shipbuilding" , "short for", "short of" , "showy", "shrapnel" , "side",
            "side by side" , "silky", "sill" , "single-decker", "single-handed" , "skateboard", "skater" , "skittle", "skittles" , "sleep like a top", "sleep off" , "slippery", "slipshod" , "smart card",
            "smarten" , "snack", "snag" , "snowed under", "snowfall" , "social worker", "socialism" , "solid", "solid fuel" , "sooner or later", "soot" , "sour", "sour grapes" , "sparring-partner",
            "sparrow" , "speechless", "speechlessly" , "spinster", "spiny" , "spoof", "spook" , "sprint", "sprinter", "stand", "stand aside" , "start/set", "starter" , "stealth",
            "stealthily" , "sterility", "sterilization" , "stinginess", "stingy" , "stooge", "stool" , "straightness", "strain" , "stretcher", "stretchy" , "stroll", "stroller" , "stupefy",
            "stupendous" , "submergence", "submersible" , "succession", "successive" , "suit (someone) down to the ground", "suit down to the ground" , "sundown", "sunflower" , "supplement",
            "supplementary" , "surround", "surrounding" , "sweat", "sweat gland" , "swing door", "swinging" , "syndicate", "syndrome" , "systolic"};
    private String m12[] = {"T" , "take (something) in good part", "take (something) into account" , "take one’s ease", "take one’s fancy" , "talk big", "talk down to" , "tap dancer",
            "tap dancing" , "Taurus", "taut" , "teaspoonful", "teat" , "telescope", "telescopic" , "tend", "tendency" , "terrify", "terrifying" , "thatched", "thaw" , "the last word",
            "the latter" , "the Supreme Court", "the theatre" , "thermostatically", "thesaurus" , "thirteenth", "thirties" , "thrilling", "thrive" , "thunderously", "thundery" , "time-consuming",
            "timeless" , "title", "title deed" , "today", "toddle" , "too bad", "tool" , "totem", "totem pole" , "tracker dog", "tracksuit" , "transfer", "transferable" , "travel light", "traveller" , "tribe",
            "tribesman" , "troop", "trooper" , "trustworthiness", "trustworthy" , "tunelessly", "tunelessness" , "tutorial", "tutu" , "tyrannically", "tyrannize" , "tyre"};
    private String m13[] = {"W" , "walrus", "waltz" , "warningly", "warp" , "watchmaker", "watchman" , "waylay", "ways and means" , "weed", "weed out" , "well-paid", "well-read" , "wheeze",
            "wheezily" , "white horse", "white lie" , "wide awake", "wide open" , "wind farm", "wind turbine" , "wire-netting", "wireless" , "witness box", "witticism" , "woollens",
            "woolliness" , "worry", "worse" , "wrinkled", "wrist" , "wysiwyg"};
    private String m14[] = {"U" , "unbeatable", "unbeaten" , "under one’s breath", "under one’s own steam" , "undue", "unduly" ,"ungraciously", "ungrammatical" , "universality", "universally" , "unrealistic", "unrealistically" ,"untiring", "untiringly" , "upon my word!", "upper" , "usefully", "usefulness" , "utterly"};
    private String m15[] = {"N" , "national service", "nationalism" , "neatly", "neatness" , "nervously", "nervousness" , "next door",
            "next of kin" , "nippy", "nit" , "non-existent", "non-fiction" ,"nosed", "nosedive", "nothing is sacred (to him/them etc)", "nothing much", "nuisance", "numb" , "nymph"};
    private String m16[] = {"o" , "obscurity", "obsequious" , "ocean", "ochre" , "off-licence", "off-white" , "old wives’ tale", "old-fashioned" , "on one’s honour/honor",
            "on one’s last legs" , "one by one", "one day" , "operate", "operatic" , "or something", "oracle" , "origins", "ornament" , "out of harm’s way", "out of it" , "outrage",
            "outrageous" , "overheads", "overhear" , "own up", "owner" , "ozone layer"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        dangkynut();
        CreateArrayListPassword();
        kiemtra();
        dangkysukien();
        ketnoi();
    }
    private void ketnoi()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {

            Class.forName(classs1);

            conn1 = DriverManager.getConnection(url1, un1,password1);

            Toast.makeText(this, "Connect Successfull!", Toast.LENGTH_SHORT).show();


        } catch (ClassNotFoundException e) {
            Toast.makeText(this, "ERROR 1" + e.toString(), Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(this, "ERROR 2" + e.getMessage(), Toast.LENGTH_SHORT).show();
            System.out.println("ERROR: " + e.getMessage());

        }
    }
    private void Readdata(String id,String name,String diachi,String sdt,String chuoiBM,String password,String friends,String brithday,String td,String mt)
    {
        try {
            st = conn1.createStatement();
            String l ="4234";
            String kk = "0a";
            //Cái này tôi lại làm kỷ niệm
            String chuoi = "insert into user" + "(iduser,user_name,Dia_Chi,Sdt,ChuoiBM,Password,friends,brithday) values"+"(?,?,?,?,?,?,?,?)";
            //Cái này tôi lại làm kỷ niệm
            String chuoi2 = "Call signupUser"+"(?,?,?,?,?,?,?,?,?,?)";
            // st.executeUpdate("insert into user(iduser,user_name,Dia_Chi,Sdt,ChuoiBM,Password,friends,brithday) values('"+l+"','"+l+"'','"+l+"','"+l+"','"+l+"','"+l+"','0','"+kk+"')");
            PreparedStatement prepStatement = (PreparedStatement) conn1.prepareStatement(chuoi2);
            prepStatement.setString(1, id);
            prepStatement.setString(2, name);
            prepStatement.setString(3, diachi);
            prepStatement.setString(4, sdt);
            prepStatement.setString(5, chuoiBM);
            prepStatement.setString(6, password);
            prepStatement.setString(7, friends);
            prepStatement.setString(8, brithday);
            prepStatement.setString(9, td);
            prepStatement.setString(10, mt);
            prepStatement .executeUpdate();
            prepStatement.close();
            //Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();

        } catch (SQLException e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            Log.d("DDDD",e.toString());
            e.printStackTrace();
        }
    }
    private void dangkynut()
    {
        img = findViewById(R.id.img_close);
        edtdc = findViewById(R.id.edt_address);
        edtmk = findViewById(R.id.edt_password);
        edtns = findViewById(R.id.edt_date_of_birth);
        _EdtSdt = findViewById(R.id.edt_phone_number);
        edtten = findViewById(R.id.edt_name);
        txt_back = findViewById(R.id.txt_lg);
        _txt_List_Password = findViewById(R.id.txt_chain);
        btndy = findViewById(R.id.btn_register);
        //btn_address = findViewById(R.id.btn_choose_Address);
        //btnthoat = findViewById(R.id.btnthoat);
        edtns.setEnabled(false);
        edtdc.setFocusable(false);
        _EdtSdt.setEnabled(false);
        edtmk.setEnabled(false);
        btndy.setEnabled(false);
    }
    private void CreateArrayListPassword()
    {
        Random random = new Random();
        int s1 = random.nextInt(m1.length);
        int s2 = random.nextInt(m2.length);
        int s3 = random.nextInt(m3.length);
        int s4 = random.nextInt(m4.length);
        int s5 = random.nextInt(m5.length);
        int s6 = random.nextInt(m6.length);
        int s7 = random.nextInt(m7.length);
        int s8 = random.nextInt(m8.length);
        int s9 = random.nextInt(m9.length);
        int s10 = random.nextInt(m10.length);
        int s11 = random.nextInt(m11.length);
        int s12 = random.nextInt(m12.length);
        int s13 = random.nextInt(m13.length);
        int s14 = random.nextInt(m14.length);
        int s15 = random.nextInt(m15.length);
        int s16 = random.nextInt(m16.length);
        int n = 37;// lấy số bằng 38 vì tổng 37 kết quả ngẫu nhiên + thêm 1 để lấy đầy đủ kết quả
        int snn = random.nextInt(n) + 1;
        if(snn == 1)
        {
            _KeyPassWord = m15[s15] + " " + m13[s13] + " " + m16[s16] + " " + m7[s7] + " " +m2[s2] + " " +m12[s12] + " " +m5[s5] + " " +m10[s10] + " " +m6[s6] + " " +m3[s3] + " " +m1[s1] + " " +m8[s8] + " " +m4[s4] + " " +m11[s11] + " " +m9[s9] + " "+ m14[s14];
        }
        if (snn == 2)
        {
            _KeyPassWord = m3[s3] + " " + m14[s14] + " "+ m15[s15] +" " + m4[s4] + " " +m1[s1] + " " + m16[s16] + " " + m5[s5] + " " +m2[s2] + " " +m6[s6] + " " +m13[s13] + " " +m8[s8] + " " +m10[s10] + " " +m11[s11] + " " +m9[s9] + " " +m12[s12] + " " +m7[s7];
        }
        if(snn == 3)
        {
            _KeyPassWord = m10[s10] + " " + m1[s1] + " " + m16[s16] + " " +  m15[s15] + " " +m11[s11] + " " +m13[s13] + " " +m2[s2] + " " +m9[s9] + " " +m12[s12] + " " +m3[s3] + " " +m7[s7] + " " +m4[s4] + " " +m6[s6] + " " +m8[s8] + " " +m5[s5];
        }
        if(snn == 4)
        {
            _KeyPassWord = m13[s13] + " " + m7[s7] + " " +m5[s5] + " " +m3[s3] + " " +m12[s12] + " " + m14[s14] + " " +m10[s10] + " " +m9[s9] + " " +m11[s11] + " " +m1[s1] + " " +m6[s6] + " " +m8[s8] + " " + m16[s16] + " " + m4[s4] + " " +m2[s2] + " " + m15[s15];
        }
        if(snn == 5)
        {
            _KeyPassWord =m14[s14] + " " + m4[s4] + " " + m13[s13] + " " +m11[s11] + " " +m12[s12] + " " +m8[s8] + " " +m1[s1] + " " +m6[s6] + " " +m7[s7] + " " + m16[s16] + " " + m2[s2]  + " " + m15[s15] + " " +m3[s3] + " " +m10[s10] + " " +m9[s9] + " " +m5[s5];
        }
        if(snn == 6)
        {
            _KeyPassWord = m2[s2] + " " + m14[s14] + " " + m5[s5] + " " +m6[s6] + " " +m1[s1] + " " +m4[s4] + " " +m3[s3] + " " +m9[s9] +" " + m15[s15] + " " +m11[s11] + " " +m7[s7] + " " +m8[s8] + " " +m10[s10] + " " + m16[s16] + " " + m12[s12] + " " +m13[s13];
        }
        if(snn == 7)
        {
            _KeyPassWord = m7[s7] + " " + m11[s11] + " " +m12[s12] + " " + m16[s16] + " " + m6[s6] + " " + m14[s14] + " " + m15[s15] + " " + " " +m10[s10] + " " +m1[s1] + " " +m4[s4] + " " +m9[s9] + " " +m8[s8] + " " +m2[s2] + " " +m5[s5] + " " +m13[s13] + " " +m3[s3];
        }
        if(snn == 8)
        {
            _KeyPassWord = m9[s9] + " " + m11[s11] + " " +m10[s10] + " " + m15[s15] + " " +m5[s5] + " " +m7[s7] + " " +m13[s13] + " " +m6[s6] + " " +m12[s12] + " " + m16[s16] + " " + m3[s3] + " " +m1[s1] + " " + m14[s14] + " " +m2[s2] + " " +m4[s4] + " " +m8[s8];
        }
        if(snn == 9)
        {
            _KeyPassWord = m8[s8] + " " + m1[s1] + " " +m11[s11] + " " + m7[s7] + " " +m10[s10] + " " +m5[s5] + " " +m9[s9] + " " +m14[s14] + " " +m2[s2] + " " + m12[s12] + " " + m15[s15] + " " +m13[s13] + " " + m4[s4] + " " +m3[s3] + " " +m6[s6] + " " +m16[s16];
        }
        if(snn == 10)
        {
            _KeyPassWord = m10[s10] + " " + m13[s13] + " " +m15[s15] + " " + m16[s16] + " " +m7[s7] + " " +m8[s8] + " " +m1[s1] + " " +m2[s2] + " " +m14[s14] + " " + m4[s4] + " " + m3[s3] + " " +m11[s11] + " " + m6[s6] + " " +m5[s5] + " " +m9[s9] + " " +m12[s12];
        }
        if(snn == 11)
        {
            _KeyPassWord = m9[s9] + " " + m2[s2] + " " +m3[s3] + " " + m1[s1] + " " +m14[s14] + " " +m12[s12] + " " +m15[s15] + " " +m8[s8] + " " +m10[s10] + " " + m11[s11] + " " + m5[s5] + " " +m13[s13] + " " + m7[s7] + " " +m4[s4] + " " +m6[s6] + " " +m16[s16];
        }
        if(snn == 12)
        {
            _KeyPassWord = m11[s11] + " " + m5[s5] + " " +m13[s13] + " " + m7[s7] + " " +m14[s14] + " " +m9[s9] + " " +m2[s2] + " " +m8[s8] + " " +m16[s16] + " " + m3[s3] + " " + m10[s10] + " " +m6[s6] + " " + m15[s15] + " " +m1[s1] + " " +m4[s4] + " " +m12[s12];
        }
        if(snn == 13)
        {
            _KeyPassWord = m5[s5] + " " + m7[s7] + " " +m11[s11] + " " + m12[s12] + " " +m16[s16] + " " +m1[s1] + " " +m4[s4] + " " +m10[s10] + " " +m14[s14] + " " + m2[s2] + " " + m6[s6] + " " +m9[s9] + " " + m13[s13] + " " +m8[s8] + " " +m15[s15] + " " +m3[s3];
        }
        if(snn == 14)
        {
            _KeyPassWord = m2[s2] + " " + m3[s3] + " " +m5[s5] + " " + m16[s16] + " " +m9[s9] + " " +m7[s7] + " " +m8[s8] + " " +m4[s4] + " " +m10[s10] + " " + m13[s13] + " " + m14[s14] + " " +m12[s12] + " " + m11[s11] + " " +m6[s6] + " " +m1[s1] + " " +m15[s15];
        }
        if(snn == 15)
        {
            _KeyPassWord = m10[s10] + " " + m5[s15] + " " + m16[s16] + " " +m9[s9] + " " +m6[s6] + " " +m14[s14] + " " +m1[s1] + " " +m11[s11] +" " + m13[s13] + " " +m7[s7] + " " +m12[s12] + " " +m8[s8] + " " +m2[s2] + " " + m3[s3] + " " + m15[s15] + " " +m4[s4];
        }
        if(snn == 16)
        {
            _KeyPassWord = m13[s13] + " " + m6[s6] + " " +m14[s14] + " " + m11[s11] + " " + m2[s2] + " " + m1[s1] + " " + m4[s4] + " " + " " +m5[s5] + " " +m10[s10] + " " +m3[s3] + " " +m12[s12] + " " +m9[s9] + " " +m8[s8] + " " +m7[s7] + " " +m15[s15] + " " +m16[s16];
        }
        if(snn == 17)
        {
            _KeyPassWord = m3[s3] + " " + m10[s10] + " " +m14[s14] + " " + m2[s2] + " " +m13[s13] + " " +m4[s4] + " " +m8[s8] + " " +m16[s16] + " " +m12[s12] + " " + m9[s9] + " " + m15[s15] + " " +m7[s7] + " " + m11[s11] + " " +m1[s1] + " " +m6[s6] + " " +m5[s5];
        }
        if(snn == 18)
        {
            _KeyPassWord = m8[s8] + " " + m7[s7] + " " +m5[s5] + " " + m4[s4] + " " +m1[s1] + " " +m14[s14] + " " +m16[s16] + " " +m15[s15] + " " +m10[s10] + " " + m11[s11] + " " + m9[s9] + " " +m12[s12] + " " + m2[s2] + " " +m13[s13] + " " +m6[s6] + " " +m3[s3];
        }
        if(snn == 19)
        {
            _KeyPassWord = m5[s5] + " " + m6[s6] + " " +m16[s16] + " " + m10[s10] + " " +m7[s7] + " " +m3[s3] + " " +m15[s15] + " " +m2[s2] + " " +m11[s11] + " " + m12[s12] + " " + m14[s14] + " " +m8[s8] + " " + m9[s9] + " " +m13[s13] + " " +m4[s4] + " " +m1[s1];
        }
        if(snn == 20)
        {
            _KeyPassWord = m14[s14] + " " + m8[s8] + " " +m13[s13] + " " + m6[s6] + " " +m7[s7] + " " +m5[s5] + " " +m15[s15] + " " +m4[s4] + " " +m12[s12] + " " + m11[s11] + " " + m16[s16] + " " +m1[s1] + " " + m15[s15] + " " +m7[s7] + " " +m16[s16] + " " +m3[s3];
        }
        if(snn == 21)
        {
            _KeyPassWord = m14[s14] + " " + m8[s8] + " " +m13[s13] + " " + m6[s6] + " " +m7[s7] + " " +m5[s5] + " " +m15[s15] + " " +m4[s4] + " " +m12[s12] + " " + m11[s11] + " " + m16[s16] + " " +m1[s1] + " " + m3[s3] + " " +m2[s2] + " " +m10[s10] + " " +m9[s9];
        }
        if(snn == 22)
        {
            _KeyPassWord = m16[s16] + " " + m5[s5] + " " +m4[s4] + " " + m1[s1] + " " +m13[s13] + " " +m2[s2] + " " +m3[s3] + " " +m12[s12] + " " +m8[s8] + " " + m7[s7] + " " + m9[s9] + " " +m6[s6] + " " + m11[s11] + " " +m14[s14] + " " +m10[s10] + " " +m15[s15];
        }
        if(snn == 23)
        {
            _KeyPassWord = m8[s8] + " " + m4[s4] + " " +m3[s3] + " " + m9[s9] + " " +m5[s5] + " " +m11[s11] + " " +m14[s14] + " " +m15[s15] + " " +m7[s7] + " " + m2[s2] + " " + m10[s10] + " " +m12[s12] + " " + m6[s6] + " " +m1[s1] + " " +m13[s13] + " " +m16[s16];
        }
        if(snn == 24)
        {
            _KeyPassWord = m8[s8] + " " + m11[s11] + " " +m4[s4] + " " + m15[s15] + " " +m16[s16] + " " +m2[s2] + " " +m10[s10] + " " +m3[s3] + " " +m1[s1] + " " + m6[s6] + " " + m7[s7] + " " +m9[s9] + " " + m12[s12] + " " +m14[s14] + " " +m5[s5] + " " +m13[s13];
        }
        if(snn == 25)
        {
            _KeyPassWord = m13[s13] + " " + m4[s4] + " " +m1[s1] + " " + m3[s3] + " " +m8[s8] + " " +m14[s14] + " " +m6[s6] + " " +m11[s11] + " " +m15[s15] + " " + m2[s2] + " " + m5[s5] + " " +m7[s7] + " " + m9[s9] + " " +m10[s10] + " " +m16[s16] + " " +m12[s12];
        }
        if(snn == 26)
        {
            _KeyPassWord = m15[s15] + " " + m4[s4] + " " +m9[s9] + " " + m5[s5] + " " +m6[s6] + " " +m10[s10] + " " +m3[s3] + " " +m11[s11] + " " +m12[s12] + " " + m7[s7] + " " + m2[s2] + " " +m8[s8] + " " + m1[s1] + " " +m13[s13] + " " +m14[s14] + " " +m16[s16];
        }
        if(snn == 27)
        {
            _KeyPassWord = m7[s7] + " " + m4[s4] + " " +m1[s1] + " " + m3[s3] + " " +m8[s8] + " " +m5[s5] + " " +m12[s12] + " " +m10[s10] + " " +m9[s9] + " " + m14[s14] + " " + m6[s6] + " " +m15[s15] + " " + m13[s13] + " " +m16[s16] + " " +m11[s11] + " " +m2[s2];
        }
        if(snn == 28)
        {
            _KeyPassWord = m10[s10] + " " + m5[s5] + " " +m13[s13] + " " + m12[s12] + " " +m7[s7] + " " +m14[s14] + " " +m8[s8] + " " +m4[s4] + " " +m6[s6] + " " + m1[s1] + " " + m11[s11] + " " +m9[s9] + " " + m15[s15] + " " +m3[s3] + " " +m2[s2] + " " +m16[s16];
        }
        if(snn == 29)
        {
            _KeyPassWord = m15[s15] + " " + m14[s14] + " " +m6[s6] + " " + m8[s8] + " " +m16[s16] + " " +m3[s3] + " " +m10[s10] + " " +m11[s11] + " " +m7[s7] + " " + m2[s2] + " " + m13[s13] + " " +m12[s12] + " " + m9[s9] + " " +m1[s1] + " " +m5[s5] + " " +m4[s4];
        }
        if(snn == 30)
        {
            _KeyPassWord = m6[s6] + " " + m9[s9] + " " +m12[s12] + " " + m4[s4] + " " +m7[s7] + " " +m13[s13] + " " +m2[s2] + " " +m16[s16] + " " +m14[s14] + " " + m5[s5] + " " + m10[s10] + " " +m11[s11] + " " + m3[s3] + " " +m15[s15] + " " +m1[s1] + " " +m8[s8];
        }
        if(snn == 31)
        {
            _KeyPassWord = m8[s8] + " " +m16[s16] + " " +m3[s3] + " " +m10[s10] + " " + m15[s15] + " " + m14[s14] + " " +m6[s6] + " " + m9[s9] + " " +m1[s1] + " " +m5[s5] + " " +m4[s4] + " " + m11[s11] + " " +m7[s7] + " " + m2[s2] + " " + m13[s13] + " " +m12[s12];
        }
        if(snn == 32 )
        {
            _KeyPassWord = m10[s10] + " " +m11[s11] + " " + m3[s3] + " " +m15[s15] + " " +m1[s1]  + " " +m7[s7] + " " +m13[s13] + " " +m2[s2] + " " +m16[s16] + " " +m14[s14] + " " + m5[s5] + " " +m8[s8] + " " + m6[s6] + " " + m9[s9] + " " +m12[s12] + " " + m4[s4];
        }
        if(snn == 33)
        {
            _KeyPassWord = m14[s14] + " " +m8[s8] + " " +m4[s4] + " " +m6[s6] + " " + m1[s1] + " " + m11[s11] + " " +m9[s9] + " " + m15[s15] + " " +m3[s3] + " " +m2[s2] + " " +m16[s16] + " " + m10[s10] + " " + m5[s5] + " " +m13[s13] + " " + m12[s12] + " " +m7[s7];
        }
        if(snn == 34)
        {
            _KeyPassWord =m6[s6] + " " + m8[s8] + " " +m16[s16] + " " +m3[s3] + " " +m10[s10] + " " +  m15[s15] + " " + m14[s14]  + " " +m11[s11] + " " +m7[s7] + " " + m2[s2] + " " + m13[s13] + " " +m12[s12] + " " + m9[s9] + " " +m1[s1] + " " +m5[s5] + " " +m4[s4];
        }
        if(snn == 35)
        {
            _KeyPassWord = m6[s6] + " " + m9[s9]  + " " +m14[s14] + " " + m5[s5] + " " + m10[s10] + " " +m11[s11] + " " + m3[s3] + " " +m15[s15] + " " +m1[s1] + " " +m8[s8] + " " + m12[s12] + " " + m4[s4] + " " +m7[s7] + " " +m13[s13] + " " +m2[s2] + " " +m16[s16];
        }
        if(snn == 36)
        {
            _KeyPassWord = m8[s8] + " " +m16[s16] + " " +m3[s3] + " " +m10[s10]  + " " +m4[s4] + " " + m11[s11] + " " +m7[s7] + " " + m2[s2] + " " + m13[s13] + " " +m12[s12]  + " " + m15[s15] + " " + m14[s14] + " " +m6[s6] + " " + m9[s9] + " " +m1[s1] + " " +m5[s5];
        }
        if(snn == 37)
        {
            _KeyPassWord = m10[s10] + " " +m11[s11] + " " + m3[s3] + " " +m15[s15] + " " +m1[s1]  + " " +m7[s7] + " " +m13[s13]  + " " +m12[s12] + " " +m2[s2] + " " +m16[s16] + " " +m14[s14] + " " + m5[s5] + " " +m8[s8] + " " + m6[s6] + " " + m9[s9] + " " + m4[s4];
        }
        _txt_List_Password.setText(_KeyPassWord);
        snn = random.nextInt(n);
    }
    private void kiemtra()
    {
        edtten.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_UP)
                {
                    if(edtten.getText().length() < 3)
                    {
                        edtmk.setEnabled(false);
                    }
                    else
                    {
                        edtmk.setEnabled(true);
                    }
                }
                return false;
            }
        });
        edtmk.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_UP)
                {
                    if(edtmk.getText().length() < 8)
                    {
                        edtns.setEnabled(false);
                    }
                    else
                    {
                        edtns.setEnabled(true);
                    }
                }
                return false;
            }
        });
        //edtdc.setFocusable(true);
        edtns.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_UP)
                {
                    if(edtns.getText().length() < 5)
                    {
                        _EdtSdt.setEnabled(false);
                    }
                    else
                    {
                        _EdtSdt.setEnabled(true);
                    }
                }
                return false;
            }
        });
        _EdtSdt.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_UP)
                {
                    if(_EdtSdt.getText().length() < 9)
                    {
                        btndy.setEnabled(false);
                    }
                    else
                    {
                        btndy.setEnabled(true);
                    }
                }
                return false;
            }
        });
    }

    private void duathongtin()
    {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("dangnhap");
        firebaseAuth = FirebaseAuth.getInstance();
    }
    private void ax2()
    {
        progressDialog = new ProgressDialog(trangdangky.this);
        progressDialog.setMessage("Please wait........");
        progressDialog.show();
        String taikhoan = edtten.getText().toString();
        String mk = edtmk.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(taikhoan.trim(),mk.trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful()) {
                    String k = firebaseAuth.getCurrentUser().getUid();
                    DatabaseReference databaseReference2 = databaseReference.child(k);
                    databaseReference2.child("Tên").setValue(taikhoan);
                    databaseReference2.child("Mật khẩu").setValue(mk);
                    databaseReference2.child("Địa chỉ").setValue(edtdc.getText ().toString());
                    databaseReference2.child("Ngày sinh").setValue(edtns.getText().toString());
                    databaseReference2.child("Số điện thoại").setValue(_EdtSdt.getText().toString());
                    databaseReference2.child("Mã key").setValue(_txt_List_Password.getText().toString());
                    databaseReference2.child("Tình trạng").setValue(false);
                    String td = dcx + ","+ dcy;
                    Readdata(k,taikhoan,edtdc.getText().toString(),_EdtSdt.getText().toString(),_txt_List_Password.getText().toString(),mk,"0",edtns.getText().toString(),td,"...");
                    taodt(k);
                    progressDialog.dismiss ();
                    finish();
                }
                else
                {
                    Toast.makeText(trangdangky.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    private void taodt(String id)
    {
        try {
            dl = new dulieusqllite(trangdangky.this, "dulieunguoidung.sqlite", null, 1);
            dl.truyvankhongtrakq("CREATE TABLE IF NOT EXISTS nguoidung(ID VARCHAR(50) PRIMARY KEY,ten VARCHAR(50),matkhau VARCHAR(100),ngaysinh VARCHAR(20),diachi VARCHAR(200),SDT varchar(50),ChuoiBM VARCHAR(500))");
            dl.truyvancoketqua("INSERT INTO nguoidung VALUES('"+id+"','"+edtten.getText().toString()+"','"+edtmk.getText().toString()+"','"+edtns.getText().toString()+"','"+edtdc.getText().toString()+"','"+_EdtSdt.getText().toString()+"','"+_txt_List_Password.getText().toString()+"')");
        }
        catch (Exception e)
        {
            Toast.makeText(trangdangky.this,"Error! An error occurred. Please try again later",Toast.LENGTH_SHORT).show();
            Log.d("AAAAAAAAAAAAAAAAAAA",e.toString());
            Intent intent = new Intent(trangdangky.this,MainActivity.class);
            startActivity(intent);
        }
    }
    private void dangkysukien()
    {
        //btnthoat.setOnClickListener(new sukiencuatoi());
        btndy.setOnClickListener(new sukiencuatoi());
        img.setOnClickListener(new sukiencuatoi());
        edtdc.setOnClickListener(new sukiencuatoi());
        txt_back.setOnClickListener(new sukiencuatoi());
    }
    private void ax()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(trangdangky.this,R.style.AlertDialogStyle);
        builder.setTitle("Notification");
        builder.setMessage ("Do you want to exit?");
        builder.setIcon(R.drawable.panda);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        Dialog dialog1 = builder.create();
        dialog1.show();
    }
    private class sukiencuatoi implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            if(view.equals(btndy))
            {
                if(edtdc.getText().length() != 0)
                {
                    duathongtin();
                    ax2();
                }
                else
                {
                    Toast.makeText(trangdangky.this,"You are not select address",Toast.LENGTH_SHORT).show();
                }
            }

            if(view.equals(img) || view.equals(txt_back))
            {
                ax();
            }
            if(view.equals(edtdc))
            {
                intent = new Intent(trangdangky.this,Address_Us.class);
                startActivityForResult(intent,key01);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode == key01 && resultCode == RESULT_OK && data != null)
        {
            Bundle bundle = data.getBundleExtra("dcc");
            String dc = bundle.getString("dcn");
            dcx = bundle.getString("dcx");
            dcy = bundle.getString("dcy");
            edtdc.setText(dc);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}