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

public class BewafaShayari extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bewafa_shayari);


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
        final String[] Bewafamsg =
                {
                        "वो तोड़ गए दिल\n" +
                                "अब बवाल क्या करें,\n" +
                                "मेरी ही पसंद थी,\n" +
                                "अब खुद से सवाल क्या करें !",

                        "ये उनकी मोहब्बत का नया दौर है,\n" +
                                "जहाँ कल मैं था आज कोई और है।",


                        "इक उम्र तक मैं जिसकी जरुरत बना रहा\n" +
                                "फिर यूँ हुआ कि उस की जरुरत बदल गई।",


                        "वो मिली भी तो क्या मिली बन के बेवफा मिली,\n" +
                                "इतने तो मेरे गुनाह ना थे जितनी मुझे सजा मिली।",

                        "ऐसे कैसे बुरा कह दूँ तेरी बेवफाई को,\n" +
                                "यही तो है जिसने मुझे मशहूर किया है।",

                        "जिनकी शायरियों में दर्द होता है\n" +
                                "वो शायर नही किसी बेवफा का दीवाना होता है।",


                        "बहुत अजीब हैं ये मोहब्बत करने वाले,\n" +
                                "बेवफाई करो तो रोते हैं और वफा करो तो रुलाते हैं।",

                        "यूँ है सबकुछ मेरे पास बस दवा-ए-दिल नही,\n" +
                                "दूर वो मुझसे है पर मैं उस से नाराज नहीं,\n" +
                                "मालूम है अब भी मोहब्बत करता है वो मुझसे,\n" +
                                "वो थोड़ा सा जिद्दी है लेकिन बेवफा नहीं।",

                        "मेरी निगाहों में बहने वाला ये आवारा से अश्क\n" +
                                "पूछ रहे है पलकों से तेरी बेवफाई की वजह।",

                        "रुशवा क्यों करते हो तुम इश्क़ को, ए दुनिया वालो,\n" +
                                "मेहबूब तुम्हारा बेवफा है, तो इश्क़ का क्या गनाह।",

                        "मत रख हमसे वफा की उम्मीद ऐ सनम,\n" +
                                "हमने हर दम बेवफाई पायी है,\n" +
                                "मत ढूंढ हमारे जिस्म पे जख्म के निशान,\n" +
                                "हमने हर चोट दिल पे खायी है।\n" +
                                "\n",

                        "ऐ दोस्त कभी ज़िक्र-ए-जुदाई न करना,\n" +
                                "मेरे भरोसे को रुस्वा न करना,\n" +
                                "दिल में तेरे कोई और बस जाये तो बता देना,\n" +
                                "मेरे दिल में रहकर बेवफाई न करना।",

                        "बेवफ़ाई का मुझे... जब भी ख़याल आता है,\n" +
                                "अश्क़ रुख़सार पर आँखों से निकल जाते हैं।",

                        "काश कि हम उनके दिल पे राज़ करते,\n" +
                                "जो कल था वही प्यार आज करते,\n" +
                                "हमें ग़म नहीं उनकी बेवफाई का,\n" +
                                "बस अरमां था कि...\n" +
                                "हम भी अपने प्यार पर नाज़ करते।",

                        "आज हम उनको बेवफा बताकर आए हैं,\n" +
                                "उनके खतो को पानी में बहाकर आए हैं,\n" +
                                "कोई निकाल न ले उन्हें पानी से..\n" +
                                "इस लिए पानी में भी आग लगा कर आए हैं।",

                        "गहराई प्यार में हो तो बेवफाई नहीं होती,\n" +
                                "सच्चे प्यार में कहीं तन्हाई नहीं होती,\n" +
                                "मगर प्यार ज़रा संभल कर करना मेरे दोस्त,\n" +
                                "प्यार के ज़ख्म की कोई दवा नहीं होती।",

                        "दिल के दरिया में धड़कन की कश्ती है,\n" +
                                "ख़्वाबों की दुनिया में यादों की बस्ती है,\n" +
                                "मोहब्बत के बाजार में चाहत का सौदा है,\n" +
                                "वफ़ा की कीमत से तो बेवफाई सस्ती है।\n" +
                                "\n",

                        "ढूंढ़ तो लेते अपने प्यार को हम,\n" +
                                "शहर में भीड़ इतनी भी न थी,\n" +
                                "पर रोक दी तलाश हमने,\n" +
                                "क्योंकि वो खोये नहीं बदल गए थे।",

                        "अपने तजुर्बे की आज़माइश की ज़िद थी,\n" +
                                "वर्ना हमको था मालूम कि तुम बेवफा हो जाओगे।",

                        "नज़ारे तो बदलेंगे ही ये तो कुदरत है,\n" +
                                "अफ़सोस तो हमें तेरे बदलने का हुआ है।",

                        "मोहब्बत से रिहा होना ज़रूरी हो गया है,\n" +
                                "मेरा तुझसे जुदा होना ज़रूरी हो गया है,\n" +
                                "वफ़ा के तजुर्बे करते हुए तो उम्र गुजरी,\n" +
                                "ज़रा सा बेवफा होना ज़रूरी हो गया है।",

                        "ये चिराग-ए-जान भी अजीब है,\n" +
                                "कि जला हुआ है अभी तलक,\n" +
                                "उसकी बेवफाई की आँधियाँ तो,\n" +
                                "कभी की आ के गुजर गईं।",

                        "कभी जो हम से प्यार बेशुमार करते थे,\n" +
                                "कभी जो हम पर जान निसार करते थे,\n" +
                                "भरी महफ़िल में हमको बेवफा कहते हैं,\n" +
                                "जो खुद से ज़्यादा हमपर ऐतबार करते थे।",


                        "जो कहते थे हमसे हैं तेरे सनम,\n" +
                                "वो दगा दे गए देखते देखते।\n" +
                                "\n" +
                                "देते मोहब्बत का इनाम क्या,\n" +
                                "वो सजा दे गए देखते देखते।\n" +
                                "\n" +
                                "सोचता हूँ कि वो कितने मासूम थे,\n" +
                                "जो बेवफा हो गए देखते देखते।",

                        "न रहा कर उदास ऐ दिल\n" +
                                "किसी बेवफा की याद में,\n" +
                                "वो खुश है अपनी दुनिया में\n" +
                                "तेरा सबकुछ उजाड़ के।",

                        "किस-किस को तू खुदा बनाएगी,\n" +
                                "किस-किस की तू हसरतें मिटाएगी,\n" +
                                "कितने ही परदे डाल ले गुनाहों पे,\n" +
                                "बेवफा तू बेवफा ही नजर आएगी।",

                        "ट्रैफिक सिग्नल पर आज उसकी याद आ गई,\n" +
                                "रंग उसने भी अपना कुछ इसी तरह बदला था।",

                        "बेवफा से दिल लगा लिया नादान थे हम,\n" +
                                "गलती हमसे हुई क्योंकि इंसान थे हम,\n" +
                                "आज जिन्हें नज़रें मिलाने में तकलीफ होती है,\n" +
                                "कुछ समय पहले उनकी जान थे हम।",

                        "छोड़ गए हमको वो अकेले ही राहों में,\n" +
                                "चल दिए रहने वो औरों की पनाहों में,\n" +
                                "शायद मेरी चाहत उन्हें रास नहीं आई,\n" +
                                "तभी तो सिमट गए वो गैर की बाहों में।\n" +
                                "\n",


                        "एक बेवफा से प्यार का अंजाम देख लो,\n" +
                                "मैं खुद ही शर्मशार हूँ उससे गिला नहीं,\n" +
                                "अब कह रहे हैं मेरे जनाज़े पे बैठ कर,\n" +
                                "यूँ चुप हो जैसे हमसे कोई वास्ता नहीं।",


                        "ये नजर चुराने की आदत\n" +
                                "आज भी नही बदली उनकी,\n" +
                                "कभी मेरे लिए जमाने से और\n" +
                                "अब जमाने के लिए हमसे।",


                        "अब भी तड़प रहा है तू उसकी याद में,\n" +
                                "उस बेवफा ने तेरे बाद कितने भुला दिए।",


                        "मुझे उसके आँचल का आशियाना न मिला,\n" +
                                "उसकी ज़ुल्फ़ों की छाँव का ठिकाना न मिला,\n" +
                                "कह दिया उसने मुझको ही बेवफा...\n" +
                                "मुझे छोड़ने के लिए कोई बहाना न मिला।",


                        "मुझे शिकवा नहीं कुछ बेवफ़ाई का तेरी हरगिज़,\n" +
                                "गिला तो तब हो अगर तूने किसी से निभाई हो।",

                        "न जाने क्या है..? उसकी उदास आंखों में,\n" +
                                "वो मुँह छुपा के भी जाये तो बेवफा न लगे।",

                        "नफरत को मोहब्बत की आँखों में देखा,\n" +
                                "बेरुखी को उनकी अदाओं में देखा,\n" +
                                "आँखें नम हुईं और मैं रो पड़ा...\n" +
                                "जब अपने को गैरों की बाहों में देखा।",

                        "जिन फूलों को संवारा था\n" +
                                "हमने अपनी मोहब्बत से,\n" +
                                "हुए खुशबू के काबिल तो\n" +
                                "बस गैरों के लिए महके।",

                        "चलो खेलें वही बाजी\n" +
                                "जो पुराना खेल है तेरा,\n" +
                                "तू फिर से बेवफाई करना\n" +
                                "मैं फिर आँसू बहाऊंगा।",

                        "जल-जल के दिल मेरा जलन से जल रहा,\n" +
                                "एक अश्क मेरे आँख में मुद्दत से पल रहा,\n" +
                                "जिसका मैं कर रहा हूँ घुट-घुट के इंतजार,\n" +
                                "वो बेवफा ना आई मेरा दम निकल रहा।",

                        "खुश हूँ कि मुझको जला के तुम हँसे तो सही,\n" +
                                "मेरे न सही... किसी के दिल में बसे तो सही।",


                        "वो बेवफा हर बात पे देता है परिंदों की मिसाल,\n" +
                                "साफ साफ नहीं कहता मेरा शहर छोड़ दो।",


                        "\u200B\u200B\u200B\u200Bदोस्त बनकर भी वो नहीं साथ निभानेवाला,\n" +
                                "वही अंदाज़ है उस ज़ालिम का ज़माने वाला।",


                        "वो कहता है... कि मजबूरियां हैं बहुत...\n" +
                                "साफ लफ़्ज़ों में खुद को बेवफा नहीं कहता।",

                        "इकरार बदलते रहते है... इंकार बदलते रहते हैं,\n" +
                                "कुछ लोग यहाँ पर ऐसे है जो यार बदलते रहते हैं",

                        "तेरे इश्क़ ने दिया सुकून इतना,\n" +
                                "कि तेरे बाद कोई अच्छा न लगे,\n" +
                                "तुझे करनी है बेवफाई तो इस अदा से कर,\n" +
                                "कि तेरे बाद कोई बेवफ़ा न लगे।",

                        "लिख-लिख कर मिटा दिए\n" +
                                "तेरी बेवफाई के गीत,\n" +
                                "किया करती थी\n" +
                                "तू भी वफ़ा एक ज़माने में।",

                        "मैंने कुछ इस तरह से खुद को संभाला है,\n" +
                                "तुझे भुलाने को दुनिया का भरम पाला है,\n" +
                                "अब किसी से मुहब्बत मैं कर नहीं पाता,\n" +
                                "इसी सांचे में एक बेवफा ने मुझे ढाला है।",

                        "गर हमें तेरी बदनामियों का डर न होता,\n" +
                                "न तू वेवफा कहती... न मैं वेवफा होता।",

                        "आज कतरा के गुजरते हुए पाया है तझे,\n" +
                                "बेवफाई का हुनर किसने सिखाया है तुझे।",

                        "उसके तर्क-ए-मोहब्बत का सबब होगा कोई,\n" +
                                "जी नहीं मानता कि वो बेवफ़ा पहले से था।",

                        "मुझे तू अपना बना या न बना तेरी खुशी,\n" +
                                "तू ज़माने में मेरे नाम से बदनाम तो है।",

                        "मुझे इश्क है बस तुमसे नाम बेवफा मत देना,\n" +
                                "गैर जान कर मुझे इल्जाम बेवजह मत देना,\n" +
                                "जो दिया है तुमने वो दर्द हम सह लेंगे मगर,\n" +
                                "किसी और को अपने प्यार की सजा मत देना।",

                        "वो जमाने में यूँ ही बेवफ़ा मशहूर हो गये दोस्त,\n" +
                                "हजारों चाहने वाले थे किस-किस से वफ़ा करते।",

                        "तेरा ख्याल दिल से मिटाया नहीं अभी,\n" +
                                "बेवफा मैंने तुझ को भुलाया नहीं अभी।",

                        "वो निकल गए मेरे रास्ते से इस कदर कि,\n" +
                                "जैसे कि वो मुझे पहचानते ही नहीं,\n" +
                                "कितने ज़ख्म खाए हैं मेरे इस दिल ने,\n" +
                                "फिर भी हम उस बेवफ़ा को बेवफ़ा मानते ही नहीं।",


                        "हर पल कुछ सोचते रहने की आदत हो गयी है,\n" +
                                "हर आहट पे चौंक जाने की आदत हो गयी है,\n" +
                                "तेरे इश्क़ में ऐ बेवफा, हिज्र की रातों के संग,\n" +
                                "हमको भी जागते रहने की आदत हो गयी है।",

                        "अगर दुनिया में जीने की चाहत न होती,\n" +
                                "तो खुदा ने मोहब्बत बनायी न होती,\n" +
                                "इस तरह लोग मरने की आरजू न करते,\n" +
                                "अगर मोहब्बत में किसी की बेवफाई न होती।",

                        "तेरा ख्याल दिल से मिटाया नहीं अभी,\n" +
                                "बेवफा मैंने तुझको भुलाया नहीं अभी।",

                        "तेरी वफ़ा के तकाजे बदल गये वरना,\n" +
                                "मुझे तो आज भी तुझसे अजीज कोई नहीं।\n" +
                                "\n",
                        "न पूछ मेरे सब्र की इन्तहां कहाँ तक है,\n" +
                                "तू सितम कर ले तेरी हसरत जहाँ तक है,\n" +
                                "वफ़ा की उम्मीद जिन्हें होगी उन्हें होगी,\n" +
                                "हमे तो देखना है तू बेवफा कहाँ तक है।",

                        "इंसान के कंधो पर इंसान जा रहा था,\n" +
                                "कफ़न में लिपटा हुआ अरमान जा रहा था,\n" +
                                "जिसे भी मिली बेवफाई मोहब्बत में,\n" +
                                "वफ़ा कि तलास में शमसान जा रहा था।",

                        "कैसे यकीन करे हम तेरी मोहब्बत का,\n" +
                                "जब बिकती है बेवफाई तेरे ही नाम से।",

                        "मोहब्बत का नतीजा दुनिया में हमने बुरा देखा,\n" +
                                "जिन्हें दावा था वफ़ा का उन्हें भी हमने बेवफा देखा।",

                        "तुझे है मशक़-ए-सितम का मलाल वैसे ही,\n" +
                                "हमारी जान है जान पर बबाल वैसे ही,\n" +
                                "चला था जिक्र जमाने की बेवफ़ाई का,\n" +
                                "तो आ गया है तुम्हारा ख्याल वैसे ही।",

                        "जब तक न लगे बेवफ़ाई की ठोकर दोस्त,\n" +
                                "हर किसी को अपनी पसंद पर नाज़ होता है।",

                        "तेरी तो फितरत थी\n" +
                                "सबसे मोहब्बत करने की,\n" +
                                "हम बेवजह खुद को\n" +
                                "खुश नसीब समझने लगे।",

                        "फिर से निकलेंगे तलाश-ए-ज़िन्दगी में,\n" +
                                "दुआ करना इस बार कोई बेवफा न निकले।",

                        "नज़र नज़र से मिलेगी तो सर झुका लेंगे,\n" +
                                "वो बेवफा है मेरा इम्तहान क्या लेगा,\n" +
                                "उसे चिराग जलाने को मत कह देना,\n" +
                                "वो ना समझ है कहीं उँगलियाँ जला लेगा।",

                        "उसके चेहरे पर इस कदर नूर था,\n" +
                                "कि उसकी याद में रोना भी मंज़ूर था,\n" +
                                "बेवफ़ा भी नहीं कह सकते उसको 'फराज़',\n" +
                                "प्यार तो हमने किया है वो तो बेक़सूर था।",

                        "अब देखिये तो किस की जान जाती है,\n" +
                                "मैंने उसकी और उसने मेरी कसम खायी है।",

                        "उसकी बेवफाई पे भी फ़िदा होती है जान अपनी,\n" +
                                "अगर उसमे वफ़ा होती तो क्या होता खुदा जाने",

                        "दो दिलों की धड़कनों में एक साज़ होता है,\n" +
                                "सबको अपनी-अपनी मोहब्बत पर नाज़ होता है,\n" +
                                "उसमें से हर एक बेवफा नहीं होता,\n" +
                                "उसकी बेवफ़ाई के पीछे भी कोई राज होता है।",

                        "बेवफाओं की इस दुनिया में संभल कर चलना,\n" +
                                "यहाँ मोहब्बत से भी बरबाद कर देतें हैं लोग।\n" +
                                "\n",

                        "इधर हमसे भी बात लाख करते हैं लगावत की,\n" +
                                "उधर गैरों से भी कुछ बादे होते जाते हैं।",

                        "जानते थे कि नहीं हो सकते कभी तुम हमारे,\n" +
                                "फिर भी खुदा से तुम्हें माँगने की आदत हो गयी,\n" +
                                "पैमाने वफ़ा क्या है, हमें क्या मालूम,\n" +
                                "कि बेवफाओं से दिल लगाने की आदत हो गयी।\n" ,

                        "कभी ग़म तो कभी तन्हाई मार गयी,\n" +
                                "कभी याद आकर उनकी जुदाई मार गयी,\n" +
                                "बहुत टूट कर चाहा जिसको हमने,\n" +
                                "आखिर में उसकी बेवफाई मार गयी।",
                        "नसीब बन कर कोई ज़िन्दगी में आता है,\n" +
                                "फिर ख्वाब बन कर आँखों में समा जाता है,\n" +
                                "यकीन दिलाता है कि वो हमारा ही है,\n" +
                                "फिर ना जाने क्यों वक़्त के साथ बदल जाता है। ",

                        "जाने मेरी आँखों से कितने आँसू बह गए,\n" +
                                "इंसानो की इस भीड़ में देखो हम तनहा रह गए,\n" +
                                "करते थे जो कभी अपनी वफ़ा की बातें,\n" +
                                "आज वही सनम हमें बेवफ़ा कह गए।",

                        "मोहब्बत से भरी कोई गजल उसे पसंद नहीं,\n" +
                                "बेवफाई के हर शेर पे वो दाद दिया करते हैं।",

                        "तेरी चौखट से सर उठाऊँ तो बेवफा कहना,\n" +
                                "तेरे सिवा किसी और को चाहूँ तो बेवफा कहना,\n" +
                                "मेरी बफओं पे सक है तो खंजर उठा लेना,\n" +
                                "मै शौक से ना मर जाऊं तो बेवफा कहना।",

                        "आप बेवफा होंगे कभी सोचा ही नहीं था,\n" +
                                "आप कभी खफा होंगे सोचा ही नहीं था,\n" +
                                "जो गीत लिखे हमने कभी तेरे प्यार पर तेरे,\n" +
                                "वही गीत रुशवा होंगे सोचा ही नहीं था।",

                        "रोये कुछ इस तरह से मेरे जिस्म से लिपट के,\n" +
                                "ऐसा लगा के जैसे कभी बेवफा न थे वो।",

                        "इस दौर में की थी जिस से वफ़ा की उम्मीद,\n" +
                                "आखिर को उसी के हाथ का पत्थर लगा मुझे।",

                        "मेरी मोहब्बत सच्ची है इसलिए तेरी याद आती है,\n" +
                                "अगर तेरी बेवफाई सच्ची है तो अब याद मत आना।",

                        "काम आ सकी न अपनी वफायें तो क्या करें,\n" +
                                "उस बेवफा को भूल न जाये तो क्या करे।",

                        "मुझे मालूम है हम उनके बिना जी नहीं सकते,\n" +
                                "उनका भी यही हाल है मगर किसी और के लिए।",

                        "वो सुना रहे थे अपनी वफाओं के किस्से,\n" +
                                "हम पर नजर पड़ी तो खामोश हो गये।",

                        "इतनी मुश्किल भी न थी राह मेरी मोहब्बत की,\n" +
                                "कुछ ज़माना खिलाफ हुआ कुछ वो बेवफा हुए।",


                        "मेरे ग़म ने होश उनके भी खो दिए,\n" +
                                "समझाते समझाते वो भी रो दिए,\n" +
                                "फिर भी ना समझा मैं उनकी बेवफाई,\n" +
                                "उनके प्यार में दोस्त यार सब खो दिए..",

                        "हमने ज़रा खता क्या की तुम नाराज़ हो गए,\n" +
                                "हम ज़रा दूर क्या हुए तुम उदास हो गए ,\n" +
                                "हम ज़रा बुरे क्या हुए तुम बेवफा हो गए,\n" +
                                "तुम ज़रा बेवफा क्या हुए हम बदनसीब हो गए..",

                        "लोगों ने सुना है की दुनिया प्यार से चलती है,\n" +
                                "कोई कहता है की दुनिया दोस्ती से चलता है क्या,\n" +
                                "लेकिन जब हमने जाना तो पाया,\n" +
                                "दुनिया सिर्फ मतलब से चलती है..",

                        "कभी करीब तो कभी जुदा था तू,\n" +
                                "जाने किस किस से खफा था तू,\n" +
                                "मुझे तो तुझ पर खुद से ज़्यादा यकीन था,\n" +
                                "पर ज़माना सच ही कहता था बेवफा है तू..",

                        "दिल से रोये मगर होठों से मुस्कुरा बैठे,\n" +
                                "यूँ ही हम किसी से वफ़ा निभा बैठे,\n" +
                                "वो हमे एक लम्हा न दे पाए अपने प्यार का ,\n" +
                                "और हम उनके लिए ज़िन्दगी लुटा बैठे।।",

                        "हमने वक़्त से बहुत वफ़ा की लेकिन ,\n" +
                                "वक़्त हमसे बेवफाई कर गया,\n" +
                                "कुछ तो हमारे नसीब बुरे थे,\n" +
                                "कुछ लोगों का हमसे जी भर गया..",

                        "क्यों मरते हो बेवफा सनम के लिए,\n" +
                                "दो गज जमीन नही मिलेगी दफन के लिए,\n" +
                                "मरना हे तो मरो देश-ए-वतन के लिए,\n" +
                                "हसीना भी दुपट्टा उतार देगी कफ़न के लिए..",

                        "ख्वाबों में बो तीर चला कर चली गई,\n" +
                                "मैं सोया था गहरी नींद जगाकर चली गई,\n" +
                                "मैने पूंछा चांद निकलता है किस तरह,\n" +
                                "तो चेहरे से अपने जुल्फ हटाकर चली गई..",

                        "मेरी आँखों में मोहब्बत की चमक आज भी है,\n" +
                                "फिर भी मेरे प्यार पर उसको शक आज भी है,\n" +
                                "नाव में बैठ कर धोये थे उसने हाथ कभी,\n" +
                                "पानी में उसकी मेहँदी की महक आज भी है..",

                        "दर्द हैं दिल में पर इसका ऐहसास नहीं होता,\n" +
                                "रोता हैं दिल जब वो पास नहीं होता,\n" +
                                "बरबाद हो गए हम उनकी मोहब्बत में,\n" +
                                "और वो कहते हैं कि इस तरह प्यार नहीं होता..",

                        "कई जन्मों से तेरे पीछे चलते रहे हैं हम,\n" +
                                "होते हुए तरल भी पिघलते रहे हैं हम।\n" +
                                "तू हो के व्यस्त भूल गया वादे हजार कर के,\n" +
                                "तेरी बेरुखी की आग में जलते रहे हैं हम।",

                        "दिल से तेरी याद को जुदा तो नहीं किया,\n" +
                                "रखा जो तुझे याद कुछ बुरा तो नहीं किया,\n" +
                                "हम से तू नाराज़ हैं किस लिये बता जरा,\n" +
                                "हमने कभी तुझे खफा तो नहीं किया।",

                        "वो कह कर गई थी कि लौटकर आऊँगी,\n" +
                                "मैं इंतजार ना करता तो क्या करता,\n" +
                                "वो झूठ भी बोल रही थी बड़े सलीके से,\n" +
                                "मैं एतबार ना करता तो क्या क्या करता..",

                        "सारे रिश्ते वो मुझ से तोड़ गई,\n" +
                                "हासिल कर के भी मुझे छोड़ गई,\n" +
                                "मेने तो दिल दिया था अपना उसके हाथों में,\n" +
                                "और वो शीशा समझ के तोड़ गई.."


                };


        ma= new MyAdapter(Bewafamsg,BewafaShayari.this);
        viewpager.setAdapter(ma);






        //for showing count of shayari on top....by sumit.....
        shayaricount = findViewById(R.id.count);
        //default set to 1
        shayaricount.setText(String.format("Bewafa Shayari : %d/%d", viewpager.getCurrentItem() + 1, Bewafamsg.length));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @SuppressLint("DefaultLocale")
            @Override
            public void onPageSelected(int position) {

                shayaricount.setText(String.format("Bewafa Shayari : %d/%d", position+1 , Bewafamsg.length));
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
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, Bewafamsg[viewpager.getCurrentItem()]);
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
                ClipData clip = ClipData.newPlainText("msg", Bewafamsg[viewpager.getCurrentItem()]);
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
