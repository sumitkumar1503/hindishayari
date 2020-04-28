package com.sumit.hindishayari;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

public class LoveShayari extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    TextView shayaricount;

    ViewPager viewpager;
    MyAdapter ma;

    ImageButton previous;
    ImageButton next;
    ImageButton whatsapp;
    ImageButton copy;

    private AdView mAdView;
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love_shayari);


        //for navigationnnnn
        setUpToolbar();
        navigationView =  findViewById(R.id.nav1);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent home= new Intent(getBaseContext(),MainActivity.class);
                        startActivity(home);
                        break;
                    case R.id.nav_dev:
                        Intent dev= new Intent(getBaseContext(),DeveloperSumit.class);
                        startActivity(dev);
                        break;
                    case R.id.nav_share:
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "Hi I have downloaded Hindi Shayari app. \nYou should try this \n" +
                                " https://play.google.com/store/apps/details?id=com.sumit.hindishayari";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hindi Shayari");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        break;
                    case R.id.nav_rate:
                        try {
                            Uri uri = Uri.parse("market://details?id="+"com.sumit.hindishayari");
                            Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(goMarket);
                        }catch (ActivityNotFoundException e){
                            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.sumit.hindishayari");
                            Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(goMarket);
                        }
                        break;
                    case R.id.nav_exit:
                        finishAffinity();
                        System.exit(0);
                        break;
                }
                return false;
            }
        });


        //for viewpager
        viewpager= (ViewPager) findViewById(R.id.vp);
        final String[] Lovemsg =
        {

                " काश तुम  पुछो मुझसे\n" +
                        "क्या चाहिये..\n" +
                        "मैं पकड़ू  हाथ और  कहु\n" +
                        "सिर्फ एक तेरा साथ चाहिये",

                "बंद होंठों से कुछ ना कहकर,\n" +
                        "\n" +
                        "आँखों से प्यार जताते हो |\n" +
                        "\n" +
                        "जब भी आते हो,\n" +
                        "\n" +
                        "हम्मे हमसे ही चुरा ले जाते हो",

                "मैं खुद हैरान हु की तुझसे\n" +
                        "♥इतनी मोहब्बत क्यू है मुझे,\n" +
                        "जब भी प्यार \uD83D\uDC98शब्द आता है\n" +
                        "चेहरा तेरा ही याद आता है….",

                "वो तेरे चश्मा नीचे करके\n" +
                        "मुझे आँख मारने की शरारती अदा !\n" +
                        "सीधी मेरे दिल में\n" +
                        "तीर चला देती है !",



                "दिल के रिश्ते कभी नहीं टूटते\n" +
                        "बस खामोश हो जाते है !",

                "इश्क़ है या कुछ और\n" +
                        "ये तो पता नहीं !\n" +
                        "पर जो तुमसे है\n" +
                        "वो किसी और से नही !",

                "कहते है जिन्दगी मे सबको,\n" +
                        "एक बार प्यार जरूर होता है !\n" +
                        "तुम्हे कब होगा मुझसे !",

                "कुछ #खास नही\n" +
                        "बस इतनी सी है मोहब्बत मेरी…!!\n" +
                        "हर रात का आखरी खयाल और\n" +
                        "हर सुबह की पहली सोच हो तुम!",


                "मै ये नहीं कहता पग्ली कि\n" +
                        "तू नहीं मिली तो जान दे दूंगा !\n" +
                        "पर एक वादा करता हूँ ,\n" +
                        "तू मिली तो ज़िन्दगी भर साथ दूंगा !",


                "मेरे चेहरे की हंसी हो तुम,\n" +
                        "मेरे दिल की हर ख़ुशी हो तुम,\n" +
                        "मेरे होंठो की मुस्कान हो तुम,\n" +
                        "धड़कता है मेरा दिल जिसके लिए,\n" +
                        "वो मेरी जान हो तुम !!",

                "मेरे दिल मेँ एक उमंग  सी छा जाती है,\n" +
                        "जब वो पगली  मेरी हर बात पे\n" +
                        "प्यार से मुझे Chal Jhutha कहती है!\n",


                "मिली थी अजनबी बनकर,\n" +
                        "आज ज़िन्दगी की जरुरत हो तुम !",

                "इतनी मुहब्बत से क्यूँ निहार रहे हैं हमे,\n" +
                        "सोच लो खुद के भी नहीं रहोगे!",

                "तुम दूर होकर ♥️ भी इतने अच्छे लगते हो,\n" +
                        "ना जाने पास होते तो कितने अच्छे लगते। \uD83C\uDF39",


                "वो दुआ ही क्या जिसमे शामिल ना हो तुम।",


                "तुम्हारी कसम मेरे सनम\n" +
                        "अब हिम्मत नहीं हारेंगे !\n" +
                        "मर जायेंगे मगर तेरे सिवा\n" +
                        "किसी और को नहीं चाहेंगे !",

                "तुम्हे खुश देखकर ही\n" +
                        "खुश हो जाता हूँ !\n" +
                        "और अब तू ही बता सच्ची\n" +
                        "मोहब्बत क्या होती है !!",


                "तुझे देखते ही बहक जाते है हम,\n" +
                        "कुछ और कहना होता है\n" +
                        "कुछ और कह जाते है हम !",

                "तेरे दिल का मेरे दिल से,\n" +
                        "रिश्ता अजीब है !\n" +
                        "मीलों की दूरियां\n" +
                        "और धड़कन करीब है !",





                "सुनो.. जब तुम *हंसती हो ना,\n" +
                        "\n" +
                        "तब और भी *Pyaari लगती हो….!!!",

                "वह मेरे लिए इतना #खास है,\n" +
                        "\n" +
                        "जितना #धड़कन को सांस है।",

                "मुलाकात नहीं #होती तो क्या हुआ\n" +
                        "\n" +
                        "#Pyaar तो फिर #बेशुमार करते हैं तुमसे।",



                "इतना प्यार तो मैंने खुद से भी नहीं किया,\n" +
                        "जितना मुझे तुमसे हो गया!!",


                "तुमको देखना और बस देखते रह जाना..\n" +
                        "मानो दरिया में उतरना और बह जाना..",


                "चेहरे पर हँसी और आँखों में नमी है |\n" +
                        "\n" +
                        "हर साँस कहती है, बस तेरी कमी है |",

                "अधूरी ज़िन्दगी महसूस होती है….\n" +
                        "\n" +
                        "मुझे तेरी कमी महसूस होती है….",

                "सब खुशियाँ तेरे नाम कर जाएंगे,\n" +
                        "\n" +
                        "ज़िन्दगी भी तुझ पे कुर्बान कर जाएँगे,\n" +
                        "\n" +
                        "तुम रोया करोगे हमें याद कर के,\n" +
                        "\n" +
                        "हम तेरे दामन में इतना प्यार भर जाऐंगे।",

                "तेरे बिना तो\n" +
                        "सिर्फ साँसे चलती हैं\n" +
                        "ज़िन्दगी तो वो\n" +
                        "होती है जब तू\n" +
                        "पास होती है",

                "तुमसे ही रूठ कर\n" +
                        "तुम्ही को याद करते हैं\n" +
                        "हमे तो ठीक से\n" +
                        "नाराज़ होना भी नहीं आता",

                "मेरे  प्यार  की  हद  न  पूछो  तुम\n" +
                        "हम  जीना  छोड़  सकते  है\n" +
                        "पर  तुम्हे  प्यार  करना  नहीं",

                "उम्र निसार दूं तेरी उस एक नज़र पे,\n" +
                        "जो तू मुझे देखे और मैं तेरा हो जाउं।",

                "दिल की किताब में गुलाब उनका था,\n" +
                        "\n" +
                        "रात की नींद में ख्वाब उनका था,\n" +
                        "\n" +
                        "कितना प्यार करते हो जब हम ने पूछा…\n" +
                        "\n" +
                        "मर जाएंगे तुम्हारे बिना ये जवाब उनका था|",

                "यूं तो किसी चीज के मोहताज नही हम,\n" +
                        "बस एक तेरी आदत सी हो गयी है।",

                "आपकी परछाई हमारे दिल में है,\n" +
                        "आपकी यादें हमारी आँखों में हैं,\n" +
                        "आपको हम भुलाएं भी कैसे,\n" +
                        "आपकी मोहब्बत हमारी सांसो में हैं।",

                "*खैर तुम मुझे समझी नही..*\n" +
                        "\n" +
                        "*मुस्कुराने के बाद में रोया क्यो था..*",



                "तमाम शहर से मैं जंग जीत सकता हूं\n" +
                        "मगर मैं तुमसे बिछड़ते ही हार जाऊंगा .",



                "मैं  चाहती  हूँ  तुम  पे\n" +
                        "सिर्फ  मेरा  हक़  हो",

                "उसकी ये मासूम अदा\n" +
                        "मुझको बेहद भाती है !\n" +
                        "वो मुझसे नाराज़ हो तो\n" +
                        "गुस्सा सबको दिखाती है !",


                "कुछ लोग खोने को प्यार कहते हैं,\n" +
                        "तो कुछ पाने को प्यार कहते हैं,\n" +
                        "पर हकीक़त तो ये है,\n" +
                        "हम तो बस निभाने को प्यार कहते हैँ",

                "मैं  ये  नहीं  कहती  की\n" +
                        "तुम्हारे  लिए  कोई  भी  दुआ  ना  मांगे ,\n" +
                        "मैं  तो  बस  यही  चाहती  हूँ  की\n" +
                        "कोई  दुआ  में  तुम्हे  ना  मांग  ले  !",


                "तू मुझे क्यों इतना याद आता है,\n" +
                        "तू मुझे क्यों इतना तड़पाता है,\n" +
                        "माना के ज़िन्दगी है सिर्फ तेरे लिए,\n" +
                        "फिर मुझे तू क्यों इतना रुलाता है",


                        "मेरी हर सांस में तू है\n" +
                        "मेरी हर ख़ुशी में तू है\n" +
                        "तेरे बिन ज़िन्दगी कुछ नrहीं\n" +
                        "क्योकि मेरी पूरी ज़िन्दगी ही तू है",

                "माना कि तू नहीं है मेरे सामने \n" +
                        "पर तू मेरे दिल में बसता हैं\n" +
                        "मेरे हर दुख में मेरे साथ होता है\n" +
                        "और हर सुख में मेरे साथ हसता है",


                "होती नहीं है मोहब्बत सूरत से,\n" +
                        "मोहब्बत तो दिल से होती है,\n" +
                        "सूरत उनकी खुद-ब-खुद लगती है प्यारी,\n" +
                        "कदर जिनकी दिल में होती है",

                "तुझे क्या पता कि मेरे दिल में,\n" +
                        "कितना प्यार है तेरे लिए,\n" +
                        "जो कर दूँ बयान तो,\n" +
                        "तुझे नींद से नफरत हो जाए!",


                "किसी को भी नहीं चाहा मैंने “Jaan“\n" +
                        "\n" +
                        "एक तुझे चाहने के बाद।",


                "किसी को चाहो तो #इतना चाहो कि\n" +
                        "\n" +
                        "किसी और को #चाहने की चाहत ना रहे।",

                "तुम कहो या ना कहो मगर फिर भी\n" +
                        "\n" +
                        "तुम्हारे हर सफर में तुम्हारे #Saath हूँ मैं।",



                "अपने प्यार पर है,\n" +
                        "इतना यक़ीन दोस्तों,\n" +
                        "कि जो हमारा हो गया,\n" +
                        "वो कभी किसी और का नहीं हो सकता…",

                "तुम्हारी आँखों की गहराई में,\n" +
                        "खोना चाहता हूँ मैं,\n" +
                        "भरकर तुम्हे अपनी बाहों में,\n" +
                        "सोना चाहता हूँ मैं",

                "आता नही था हमें इकरार करना,\n" +
                        "ना जाने कैसे सीख गये प्यार करना,\n" +
                        "रुकते ना थे दो पल कभी किसी के लिए,\n" +
                        "ना जाने कैसे सीख गये इंतेज़ार करना!!",

                "मत पूछ वजह की क्यू\n" +
                        "चाहती हूँ तुझे\n" +
                        "क्योंकि साचा इश्क वजह से नहीं\n" +
                        "बेवजह होता है",


                "तेरा रूठना भी इतना अच्छा लगता है,\n" +
                        "कि दिल करता है दिनभर तुझे छेड़ता ही रहूँ",


                "तेरा रूठना भी इतना अच्छा लगता है,\n" +
                        "कि दिल करता है दिनभर तुझे छेड़ता ही रहूँ",

                        "प्यार मैं तुझसे करती हूँ,\n" +
                        "और अपनी जिंदगी से ज्यादा करती हूँ।",

                "वो खुद पर इतना गुरूर करते हैं,\n" +
                        "तो इसमें हैरत की बात नहीं,\n" +
                        "जिन्हें हम चाहते हैं,\n" +
                        "वो आम हो ही नहीं सकते !!",



                "सिर्फ गुलाब देने से अगर मोहब्बत हो जाती,\n" +
                        "तो माली सारे ‘शहर’ का महबूब बन जाता",

                "दर्द की जब कभी इन्तहा होती हैं\n" +
                        "दवा की जरुरत फिर कहाँ होती हैं\n" +
                        "तन्हाई, बेचैनी और बस कुछ आहें\n" +
                        "इनमे पल कर ही मोहब्बत जवां होती हैं",



                "तन्हाई भी मेरे पास आने से डरती है,\n" +
                        "उसे भी मालूम होगा\n" +
                        "कि हमे कोई अपनी जान❤️से भी ज्यादा चाहता है।",


                "अच्छा लगता है \uD83E\uDD17 जब कोई कहता है,\n" +
                        "कोई बात नहीं, मै हूं ना तुम्हारे साथ। \uD83D\uDE42",

                "ये तो तुम्हारे महोब्बत❤️का नतीजा है,\n" +
                        "वर्ना हम इतनी आसानी से सुधरने वाले नहीं थे।",


                "दिल नहीं लगता आपको देखे बिना ;\n" +
                        "दिल नहीं लगता आपके बारे में सोचे बिना;\n" +
                        "आँखें भर आती हैं यह सोच कर;\n" +
                        "कि किस हाल में होंगे आप हमारे बिना❤️।",

                "ऐ खुदा मुझे जब बनाए\n" +
                        "थोड़ी सी मिट्टी उसकी मिला देना\n" +
                        "तू मुझे उसका नही बना सकता\n" +
                        "तो उस जैसा मुझे बना देना",


                "सुना है ..\n" +
                        "\n" +
                        "इन बादलो के पार है इक रूहों का शहर ..\n" +
                        "\n" +
                        "हम मिलेंगे वहाँ.. तुम मेरा नाम याद रखना..!!",

                "ये मेरा इश्क, औरों सा नहीं,,,,,,,,\n" +
                        "\n" +
                        "तन्हा रहूँगा.. फिर भी तेरा ही रहूँगा….!",

                "अभी तक समझ नहीं पाये तेरे इन फैसलो\n" +
                        "को\n" +
                        "ऐ खुदा..!\n" +
                        ".\n" +
                        "उसके हक़दार हम नहीं..\n" +
                        "या…\n" +
                        "हमारी दुआओ में दम नहीं.",


                "एक रोज #तुमसे हम #जरूर मिलेंगे,\n" +
                        "\n" +
                        "Dil के सारे #अरमान कहेंगे…\n" +
                        "\n" +
                        "तुम हमारी #सांसें बनना….\n" +
                        "\n" +
                        "हम तुम्हारी #Jaan बनेंगे।",



                "ये सर्द हवाएं कह रही है,\n" +
                        "तुझे गले से लगा लूँ!\n" +
                        "छुप जाऊँ तेरी बाहों में !\n" +
                        "और दुनिया को भुला दूँ_",

                "*तेरी\uD83D\uDC69 आँखों के\uD83D\uDD6Fजादू से*\n" +
                        "*तू ख़ुद नहीं है \uD83E\uDD14वाकिफ़;*\n" +
                        "*ये \uD83D\uDC64उसे भी जीना सीखा देता*\n" +
                        "*जिसे मरने \uD83D\uDECCका शौक़ हो*",



                "“तेरे मिलने की आस न होती;\n" +
                        "तो ज़िंदगी आज यूँ उदास न होती;\n" +
                        "मिल जाती कभी तस्वीर जो तेरी;\n" +
                        "तो हमको आज तेरी तलाश न होती।”",

                "ना ये चाँद चाहिए, ना ये फलक चाहिए,\n" +
                        "मुझे सिर्फ तुम्हारी ये झलक चाहिए!!",


                "मोहब्बत तो दिल से की थी,\n" +
                        "दिमाग उसने लगा लिया !\n" +
                        "दिल तोड दिया मेरा उसने,\n" +
                        "और इल्जाम मुझपर लगा दिया !",

                "मुझे अच्छा लगता है तुझे\n" +
                        "मनाना इसीलिये तुझे बिना\n" +
                        "वजह परेशान करता हूँ !",

                "❤ ऐ हवा …..\n" +
                        "\n" +
                        "जाकर उसका दिल धडका दें\n" +
                        "उसे याद दिला दें\n" +
                        "\n" +
                        "कि कोई हैं जो उसे\n" +
                        "हर पल याद करता हैं ……❤\n",


                "किसी ने पूछा .\n" +
                        "दिल की खूबी क्या है???\n" +
                        "\n" +
                        "हमने कहा….\n" +
                        "\n" +
                        "हजारो ख्वाहिशों के नीचे\n" +
                        "दबकर भी धड़कता है….",


                "वो बोले हमसे. . .\n" +
                        "मुझसे इश्क ना करो,\n" +
                        "\n" +
                        "हमने कहा, कर कौन रहा है\n" +
                        "वो तो हो रहा है . .\n" +
                        "\uD83D\uDC9E\uD83D\uDC9E",





                "सुनो\uD83D\uDCE3 मेरी #जान\uD83D\uDE0D\n" +
                        "#प्यार से प्यारी\uD83D\uDC93 मेरी #दिलरुबा,\n" +
                        "मेरा #ग़म\uD83D\uDC94 कोई भी हो\n" +
                        "मेरी #ख़ुशी \uD83D\uDE18तुम ही हो #सिर्फ़\uD83D\uDC49 तुम..!",

                " पता है❓ #हमें #प्यार करना #नहीं \n ✖️ आता \uD83D\uDE1Fमगर,\n" +
                        "#जितना भी \uD83D\uDE0A#किया है \n #सिर्फ\uD83D\uDC49 तुमसे ❤️किया है...!",

                "तुम भी मोहब्बत❤️ का सौदा \nबडी अजीब करते हो……\n" +
                        "थोडा सा मुस्कुरा देते हो\n" +
                        "और दिल खरीद लेते हो…",


                "कर्ज़ होता तो उतार भी देते…\n" +
                        "\n" +
                        "कमबख़्त “इश्क़” था… चढ़ारहा",

                "खुशबू बन बिखर रहा हूँ\n" +
                        "तेरे ख्यालो की आतिश से पिघल रहा हूँ\n" +
                        "यू न देना कोई दोष अब मुझको\n" +
                        "मैं तेरे ताब में फिर बहक रहा हूँ",

                "उन्होंने कहा बहुत बोलते हो….\n" +
                        "अब क्या बरस जाओगे?\n" +
                        "हमने कहा…..\n" +
                        "चुप हो गये ना, तरस जाओगे ।।",


                "नज्मों से ना तोलो जज़्बातों को\n" +
                        "\n" +
                        "कागज़ पर उतारने और….✋\uD83C\uDFFB\n" +
                        "दिल पर गुज़रने में फर्क होता है!",

                "साँसों की तरह\n" +
                        "तुम भी शामिल हो मुझमें….\n" +
                        "\n" +
                        "रहते भी साथ हो\n" +
                        "और ठहरते भी नहीं…..",

                "सुरमे की तरह पीसा है हमें हालातों ने,\n" +
                        "\n" +
                        "तब जा के चढ़े है लोगों की निगाहों में",

                "जो हारते हैं अपनी पहली मोहब्बत\n" +
                        "दूसरे इश्क मे वो कमाल करते हैं\n" +
                        "✍\uD83D\uDC94",

                "\uD83D\uDC93\uD83D\uDC93इश्क़ है तो\n" +
                        "शिकायत न कीजिए …!!!\uD83D\uDC93\uD83D\uDC93\n" +
                        "\n" +
                        "\uD83D\uDC93\uD83D\uDC93और शिकवे हैं तो\n" +
                        "मोहब्बत न कीजिए …!!\uD83D\uDC93\uD83D\uDC93",

                "एक एक पल गुलाब हो जाता\n" +
                        "लम्हा लम्हा किताब हो जाता\n" +
                        ".\n" +
                        "मेरी नजरो को जो पढा होता\n" +
                        "सारा पानी शराब हो जाता",

                "अगर छोड़ दूँ कलम तो…..\n" +
                        "तेरी यादें मर जायेँगी…!!\n" +
                        "\n" +
                        "और अगर छोड़ दूँ तेरी यादों\n" +
                        "को…\n" +
                        "तो मैं मर जाऊँगा",

                "देर लगेगी मगर सही होगा…!\n" +
                        "\n" +
                        "हमें जो चाहिए वही होगा…!\n" +
                        "\n" +
                        "दिन बुरे है.., ज़िंदगी नहीं .\uD83C\uDF39",

                "तुम सुबह सुबह\n" +
                        "याद ना आया करो\n" +
                        "\n" +
                        "मेरी चाय रोज ही\n" +
                        "ठंडी हो जाती है",

                "रोज़ आ जाती हो बिना\n" +
                        "इत्तेला दिए ख्वाबों में\n" +
                        "\n" +
                        "कोई देख लेगा तो\n" +
                        "हम क्या जवाब देंगे",

                "आज हवा के रुख में जो नरमाई है ।।\n" +
                        "हमदर्दी है या कुछ और समाई है ।।",

                "तुम को नाराज़ ही रहना है तो कुछ बात करो,\n" +
                        "\n" +
                        "चुप रहते हो तो मुहब्बत का गुमान होता है.!\n" +
                        "\uD83D\uDC90\uD83D\uDC9A \uD83D\uDC90",

                "बात बात पर\n" +
                        "इंम्तिहान क्यों लेते हो…\n" +
                        "\uD83D\uDC9E\uD83D\uDC9E\uD83D\uDC9E\uD83D\uDC9E\uD83D\uDC9E\uD83D\uDC9E\n" +
                        "ले लिया दिल तो\n" +
                        "अब जान क्यों लेते हो…",

                "इतनी जल्दी तुम्हे दिल कैसे दे दे\n" +
                        "\n" +
                        "अभी सीखो तुम दिल की हिफाज़त करना☝❤",

                "तुम अच्छे हो तो बन के दिखाओ\n" +
                        "\n" +
                        "हम बुरे है तो साबित करो",

                "फ़ासले तुम और बढ़ाओ,\n" +
                        "ऐतराज़ हमनें कब किया है\n" +
                        "\n" +
                        "तुम भी भुला ना पाओगे मुझको\n" +
                        "वो मस्त अंदाज़ हूँ मैं",

                "मेरे अल्फाजों से लोग\n" +
                        "बात भी करते हैं\n" +
                        "\n" +
                        "वो कहते हैं अब तुम्हारे\n" +
                        "अल्फाजों से आवाज आती है",

                "सरे राह जो उनसे नज़र मिली,\n" +
                        "\n" +
                        "तो नक़्श दिल के उभर गए;\n" +
                        "\n" +
                        "हम नज़र मिला कर झिझक गए,\n" +
                        "\n" +
                        "वो नज़र झुका कर चले गए।",

                "एक छोटा सा फर्क हैं।\n" +
                        "यादोंऔर वादों_में\n" +
                        "वादें हम तोड़ देते हैं\n" +
                        "ओर यादें हमे तोड़ देती_हैं।\n" +
                        "\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83D\uDC94",


                "गुलाबों की तरहा जिंदगी\n" +
                        "महक जाएगी जनाब\uD83C\uDF3F\uD83C\uDF43\n" +
                        "कांटों से तालमेल की\n" +
                        "अदा सीख लीजिए !\uD83C\uDF39\uD83E\uDD8B\uD83C\uDF42",


                "बदल दिया है मुझे,\n" +
                        "मेरे चाहने वालो ने ही,\n" +
                        "\n" +
                        "वरना मुझ जैसे शख्स में,\n" +
                        "इतनी खामोशी कहाँ थी..",



                "Khushboo Bankar Teri Saanso Mein\n Sama Jayenge❣️,\n" +
                        "Sukun Bankar Tere Dil Mein \nUtar Jayenge❣️,\n" +
                        "MehsusKarne Ki Koshish To Kijiye❣️,\n" +
                        "Door Rahte Hue Bhi Pass\n Nazar Aayenge❣️.",


                "\uD83C\uDF39मुश्किल भी तुम हो,\n हल भी तुम हो ,\nहोती है जो सीने में ,\n वो हलचल भी तुम हो ..!!\uD83C\uDF39",




                "हिम्मत इतनी \uD83D\uDC9D तो नहीं मुझमे \uD83E\uDDD1\uD83C\uDFFB.\uD83D\uDE4F\n" +
                "कि तुझे \uD83D\uDC71\uD83C\uDFFB\u200D♀ दुनियां \uD83C\uDF0D से छीन लूं \uD83C\uDF39,\uD83D\uDC4D\n" +
                "लेकिन मेरे \uD83E\uDDD1\uD83C\uDFFB दिल \uD83D\uDC98 से कोई \uD83D\uDC9E तुझे \uD83D\uDC71\uD83C\uDFFB\u200D♀ \n निकाले.!\uD83D\uDE48\n" +
                "इतना हक \uD83D\uDCAA\uD83C\uDFFB तो मैंने \uD83E\uDDD1\uD83C\uDFFB खुद ✋\n को भी नहीं ❌ दिया…..\uD83D\uDE0E\uD83D\uDE18",


        };


        ma= new MyAdapter(Lovemsg,LoveShayari.this);
        viewpager.setAdapter(ma);






        //for showing count of shayari on top....by sumit.....
        shayaricount = findViewById(R.id.count);
                //default set to 1
        shayaricount.setText(String.format("Love Shayari : %d/%d", viewpager.getCurrentItem() + 1, Lovemsg.length));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @SuppressLint("DefaultLocale")
            @Override
            public void onPageSelected(int position) {

                shayaricount.setText(String.format("Love Shayari : %d/%d", position+1 , Lovemsg.length));
            }


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        // next button
        next=(ImageButton)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=viewpager.getCurrentItem();
                if ((pos+1)==ma.getCount()){
                    viewpager.setCurrentItem(0);
                }else{
                    viewpager.setCurrentItem(pos+1);
                }
            }
        });



        //previous button
        previous=(ImageButton)findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=viewpager.getCurrentItem();
                if (pos==0){
                    viewpager.setCurrentItem(ma.getCount());
                }else{
                    viewpager.setCurrentItem(pos-1);
                }
            }
        });


        //for whatsapp
        whatsapp = findViewById(R.id.whatsapp);
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, Lovemsg[viewpager.getCurrentItem()]);
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getBaseContext(),"Whatsapp Not Installed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //for copy
        copy = findViewById(R.id.copy);
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("msg", Lovemsg[viewpager.getCurrentItem()]);
                assert clipboard != null;
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getBaseContext(),"Copied To Clipboard !",Toast.LENGTH_SHORT).show();
            }
        });

// for addddddd
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    //for navigation bar
    private void setUpToolbar(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer1);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}



