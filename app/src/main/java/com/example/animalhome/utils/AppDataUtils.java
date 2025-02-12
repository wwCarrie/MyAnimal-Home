package com.example.animalhome.utils;

import android.content.ContentValues;

import com.example.animalhome.R;
import com.example.animalhome.db.BusinessResult;
import com.example.animalhome.db.FindDB;
import com.example.animalhome.entity.AnimalKnowledge;
import com.example.animalhome.entity.AnimalSpecies;
import com.example.animalhome.entity.Find;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AppDataUtils {

    private static List<AnimalSpecies> animalSpeciesList = new ArrayList<>();

    private static final List<AnimalKnowledge> animalKnowledgeList = new ArrayList<>();

    /**
     * 注意！！
     * 注意！！
     * 注意！！
     * 当前数据编辑以后，需要卸载app，重新安装才能生效
     */
    public static void init() {
        // 初始化数据
        BusinessResult<List<Find>> result = FindDB.getAllFindList();
        if (result.isSuccess() && !result.getData().isEmpty()) {
            return;
        }
        String find1url = ImagePreStorageUtils.saveDrawableImage(R.drawable.pic_find1, "pic_find1");
        String find2url = ImagePreStorageUtils.saveDrawableImage(R.drawable.pic_find2, "pic_find2");
        String find3url = ImagePreStorageUtils.saveDrawableImage(R.drawable.pic_find3, "pic_find3");
        String find4url = ImagePreStorageUtils.saveDrawableImage(R.drawable.pic_find4, "pic_find4");
        String find5url = ImagePreStorageUtils.saveDrawableImage(R.drawable.pic_find5, "pic_find5");
        String find6url = ImagePreStorageUtils.saveDrawableImage(R.drawable.pic_find6, "pic_find6");
        //添加一些预设的发现，方便测试
        if (!find1url.isEmpty()) {
            Find find1 = new Find();
            find1.setTitle("#狗狗 #动物 #动物之家");
            find1.setUrl(find1url);
            FindDB.addFind(find1);
        }
        if (!find2url.isEmpty()) {
            Find find2 = new Find();
            find2.setTitle("#兔子 #棕色兔子 #动物 #动物之家");
            find2.setUrl(find2url);
            FindDB.addFind(find2);
        }
        if (!find3url.isEmpty()) {
            Find find3 = new Find();
            find3.setTitle("#这是鸟吗？ #动物 #动物之家");
            find3.setUrl(find3url);
            FindDB.addFind(find3);
        }
        if (!find4url.isEmpty()) {
            Find find4 = new Find();
            find4.setTitle("#狗子 #萨摩耶？ #动物 #动物之家");
            find4.setUrl(find4url);
            FindDB.addFind(find4);
        }
        if (!find5url.isEmpty()) {
            Find find5 = new Find();
            find5.setTitle("#可达鸭 #红烧可达鸭 #动物 #动物之家");
            find5.setUrl(find5url);
            FindDB.addFind(find5);
        }
        if (!find6url.isEmpty()) {
            Find find6 = new Find();
            find6.setTitle("#猫咪 #头像 #动物 #动物之家");
            find6.setUrl(find6url);
            FindDB.addFind(find6);
        }
    }


    public static List<AnimalSpecies> getAnimalSpeciesList() {
        if (!animalSpeciesList.isEmpty()) {
            return animalSpeciesList;
        }
        //获取assets目录下的animal.json文件
        try {
            InputStream open = AppUtils.getApplication().getResources().getAssets().open("animal.json");
            int length = open.available();
            byte[] bytes = new byte[length];
            open.read(bytes);
            String result = new String(bytes);
            animalSpeciesList = JsonUtils.parse(result, new TypeToken<List<AnimalSpecies>>() {
            });
        } catch (Exception ignored) {
        }
        return animalSpeciesList;
    }

    public static List<AnimalKnowledge> getAnimalKnowledgeList() {
        if (!animalKnowledgeList.isEmpty()) {
            return animalKnowledgeList;
        }
        AnimalKnowledge animalKnowledge1 = new AnimalKnowledge();
        animalKnowledge1.setImgResId(R.drawable.pic_animal_knowledge1);
        animalKnowledge1.setTitle("刚出生的小奶猫眼睛是什么颜色的？");
        animalKnowledge1.setContent("很多不了解猫咪的人都会觉得它很高冷，但是仔细接触下来，会发现猫咪身上也有很多的不同于狗狗的优点，这也可能就是人类喜欢猫咪的原因吧。\n\n第一、猫咪是名十分优秀的跳高者，猫咪一跃就能够跳出差不多像自己身高六倍高的高度，可以说是十分的厉害。\n\n第二、猫咪得智商非常的高，它们非常的聪明，从猫咪的大脑结构角度来看，它的大脑结构是不逊色的，与人脑的结构高度相似。但是因为它不会像人类一样说话，也懒于表达自己的想法。\n\n第三、猫咪有十分敏锐的听觉，猫咪的听觉能够听到的声音是人类能听到声音的四倍，能听到一些低分贝人类无法听到的声音。\n\n第四、猫咪的味觉有所偏颇，当给猫咪喂带有甜味的食物时，猫咪是品尝不出其中的甜味的。但是猫咪对水的敏感度高，品尝的出饮用水的不同味道，猫咪尤其喜欢流动的新鲜水，如果铲屎官给猫咪提前备好了温的静水的话，猫咪是不会青睐的。\n\n第五、有的铲屎官会给猫咪准备专门排泄的猫砂盆，细心观察的会发现猫咪在排泄完后，会把在猫砂盆的粑粑偷偷埋起来，有的铲屎官可能会觉得是无意的或者是淘气在玩弄，其实不是的，这是猫咪在试图掩盖自己排泄物气味，表达自己对铲屎官的听话。如果你的猫咪排泄完后并没有埋屎，那可能是它没有把你放在眼里。\n\n第六、平常在看猫咪的时候，大多从猫的种类、外表的颜色花样来发现猫咪的不同，如果大致相似就觉得差不多，殊不知每只猫咪鼻子上的纹理都是不同的，每一只猫咪都有它特别的纹理，就像每个人的指纹都一样是的。\n\n第七、铲屎官平时在养喵咪的时候会发现，猫咪有时候会喜欢用它的脸蹭你，除了想要表达喜欢和撒娇之外，猫咪用脸蹭蹭你也是想在你身上留下自己气味，觉得这样是对外界宣布自己的所有权，也是有股霸道总裁范。");
        animalKnowledgeList.add(animalKnowledge1);

        AnimalKnowledge animalKnowledge2 = new AnimalKnowledge();
        animalKnowledge2.setImgResId(R.drawable.pic_animal_knowledge2);
        animalKnowledge2.setTitle("千万不要轻易收养流浪猫");
        animalKnowledge2.setContent("有个网友给我留言，讲述了他收养流浪猫半年时间的“心酸经历”。\n\n在这半年时间里，他的心态从开始时的心疼，变成了无奈，又变成了愤怒，最后变成了后悔。\n\n尽管被流浪猫屡次伤害，可他依旧没有打算放弃；可流浪猫自己离开的行为，还是让他感到付出的努力全都浪费了。\n\n一次偶然的机会，网友在自己的车下面发现了一只流浪猫。\n\n那是一只狸花猫，发现这只猫咪的时候它很瘦弱，很脆弱，并且身上还有伤口。\n\n网友家里养了一只猫，再加上他本人喜欢猫，于是就心生怜悯把猫咪带回了家。\n之后，他先带着流浪猫去宠物医院处理伤口，又给猫咪打了疫苗。\n\n可是，养猫的心酸和困难才刚刚开始。\n\n这只流浪猫脾气出奇的差，尽管他付出了耐心，试图感动这只猫，可换来的是被流浪猫一次次抓挠。\n\n开始的时候，他没敢让流浪猫和自家的宠物猫接触，担心两只猫打架。随着时间推移，他决定试试看，两只猫是否能接受对方。\n\n没想到，刚把两只猫放在一起，流浪猫就把宠物猫咬伤了。\n\n从那之后，他再也不敢将两只猫放在一起。\n\n可是，噩耗才刚刚开始。这只流浪猫咬人，抓人，主动攻击他的家人；\n\n家里的垃圾桶，厨房里面的东西，家里的纸箱子，沙发，全都难逃这只猫咪的“磨爪”。\n\n无论他用什么方法，似乎都无法获得这只流浪猫的信任，无法让猫咪的暴躁之心平复下来。\n\n再后来，大概养了猫咪半年时间，猫咪自己抓坏了纱窗，从家里的2楼逃走了。\n\n从那之后，流浪猫再也没回来过。\n\n网友十分感慨：半年的付出就这样没了，又是心酸，又是心疼。\n\n他说：以后再也不敢收养流浪猫了。");
        animalKnowledgeList.add(animalKnowledge2);

        AnimalKnowledge animalKnowledge3 = new AnimalKnowledge();
        animalKnowledge3.setImgResId(R.drawable.pic_animal_knowledge3);
        animalKnowledge3.setTitle("“流浪猫群体”到底是如何产生的？");
        animalKnowledge3.setContent("当我们在谈论流浪猫时，到底在谈论什么？\n爱猫并且养猫的铲屎官们，会想到慈悲，会想到同情，会想到自己家的猫；没有养过猫，不喜欢猫的人，会以冷漠的态度对待，与我无关。\n而仇视猫狗的人，会视它们为垃圾，做出一些伤害流浪猫狗的行为。\n可是没有多少人讨论，流浪猫的存在，是如何产生的？流浪猫聚集的范围和群体，是如何一点点扩大的？\n流浪猫群体产生的原因：\n第一：走丢的猫和被遗弃的猫\n归根结底，外面产生的流浪猫，都是从养猫的人家中主动或者被动走出去的。\n有些猫，是因为主人在养猫的过程中粗心大意，并没有封闭好家里的门窗，导致猫咪趁着一些机会从门窗跑出去，然后再也没回去；而这些偷偷跑出去的猫，就成为了流浪猫第一批源头；当然，自己走丢的宠物猫变成了流浪猫，毕竟是少数。\n更多的来源则在于，那些养猫的人，丢掉了自己家养的猫。\n我见过不少例子，看到过不少网友发的帖子；有些人在外面租房工作，买一只猫养来陪伴自己；辞掉工作回老家发展后，猫怎么办？没有能力带回老家，只能选择丢掉。\n与租房工作的人类似，还有一些在大学阶段读书的人，会养一只猫陪在自己身边；可是毕业了，需要去别的地方工作了，猫咪怎么办？\n那些盲目跟风养猫，看到别人养猫自己也想养一只，只是出于好奇心，却不愿意在猫咪身上投入成本的人，同样是丢猫的源头。\n他们不舍得给猫咪消费，不愿意在猫咪身上投资；一旦猫咪需要的，他们无法给予，最后他们就会丢掉毛。\n反正也不喜欢，丢掉就是了。\n而这些主动或被动走丢的猫咪，就是一大群流浪猫群体产生的源头。\n");
        animalKnowledgeList.add(animalKnowledge3);

        AnimalKnowledge animalKnowledge4 = new AnimalKnowledge();
        animalKnowledge4.setImgResId(R.drawable.pic_animal_knowledge4);
        animalKnowledge4.setTitle("养流浪猫的注意事项");
        animalKnowledge4.setContent("流浪猫可爱又可怜，有的被主人遗弃，有的则是一出生就成为了流浪猫。很多人心生怜悯，去喂养或者收养流浪猫，给流浪猫一个安身之地，下面就为好心人们提供一些喂养或者收养流浪猫的建议，提醒朋友们在爱护小动物的同时也保护好自己。\n不要过于亲近流浪猫，喜欢是好的，不过毕竟不太清楚猫猫接触过什么病菌，如果你并不负责给猫猫洗澡澡的话，就做简单的接触就好了。接触流浪猫后一定要及时洗手，尤其是对皮肤敏感的朋友来说。\n注意猫咪的动作，分辨好猫猫现在的状态，不要误把警惕当卖萌，现在网上很多猫咪的视频看起来很可爱，其实猫咪当时在紧张状态或者正在生气，如果这时候你伸手去摸它，有可能会因为害怕而攻击人。\n与猫咪玩耍时，注意不要让猫咪误伤到自己，最好穿着长袖衣裤，不要穿着凉鞋。\n如果有条件，特别喜欢一只的话，建议领养回家，因为这样可以对猫咪进行更有效的管理，也能对猫咪进行更好的保护。那么领养流浪猫又应该注意什么呢？\n及时对猫咪进行清洗，最好用专门为宠物洗澡的沐浴露，对流浪猫进行彻底清洗，清洗时应该佩戴好手套，猫咪中的大部分是怕水的，尤其是对流浪猫咪来说，接触水有可能会让它们因为害怕而攻击人。可以使用猫咪洗澡专用的洗猫袋将猫咪装进去再洗。\n带猫咪去做体检，打疫苗，这是必备环节，排除猫咪带有的疾病，并做好预防工作，以便顺利过渡为家猫。\n结扎手术还是要做的，如果不接扎的话，猫咪在发情期间可能会到处撒尿，而且一旦乱尿留下气味后，以后它们会经常去那撒尿。\n购买猫砂，引导猫猫去指定的位置尿尿，如果猫猫尿到了别处，一定要擦干净，不要留下余味，原因上面已经说啦。如果耐心引导猫猫去厕所的话那就更好了，不过流浪猫收养回来毕竟都比较大了，不太容易驯服了。\n");
        animalKnowledgeList.add(animalKnowledge4);

        AnimalKnowledge animalKnowledge5 = new AnimalKnowledge();
        animalKnowledge5.setImgResId(R.drawable.pic_animal_knowledge5);
        animalKnowledge5.setTitle("关于流浪猫的小知识");
        animalKnowledge5.setContent("你知道吗？一只流浪猫，能够吸引到多只流浪猫的聚集。\n尤其是在春夏季节，一只发情期的母猫，更是能够吸引到附近多只流浪猫公猫的聚集。\n母猫一年的繁殖次数，多达3-4次，何况猫咪每一次生产，都能生下多只小猫。\n当这些幼猫经历了生活的筛选，熬过了生存的难关，它们又会成长，变成新的一批流浪猫。\n而当猫咪超过8个月之后，一般就具备繁殖的能力；然后，通过流浪猫的不断聚集，不断繁殖，又产生新的一批流浪猫。\n这样的速度，是很可怕的；每一年，都会有相当庞大的幼猫在产生。\n如果你仔细观察就会发现：\n倘若你居住的环境周围，有几只流浪猫出现；这几只猫有人喂食，有居住的地方，那么过不了多久，这周围就会聚集来更多的流浪猫。\n流浪猫更换居住环境最大的变数在于：食物和居住地。\n");
        animalKnowledgeList.add(animalKnowledge5);

        AnimalKnowledge animalKnowledge6 = new AnimalKnowledge();
        animalKnowledge6.setImgResId(R.drawable.pic_animal_knowledge6);
        animalKnowledge6.setTitle("落难帅猫获救记");
        animalKnowledge6.setContent("要问校园里最好看的长毛猫是谁？\n\n那一定是无德。\n无德是一只绝育小公猫，因为他帅气的脸庞获得了众多小姐姐的喜欢。无德虽然帅气却没有猫德，因为他仗着自己长得帅气曾在光天化日之下公然欺负小母猫，得名无德。\n\n无德之前一直生活在南校操场，但是不知道从哪天起，帅气的无德突然不见了。江湖上不见了无德的身影，但到处都是他的传说。\n\n日子就这样一天天过去了，无德渐渐消失在了大家的视野中。突然有一天，理毛志愿者听到南校管理学院教学楼中有猫咪的求救声。循着声音找过去，发现在男厕的通风管狭小缝隙中有一只猫咪，不断向外求救。\n\n\n\n黑暗中用手电筒照射只能看到猫咪的眼睛，无法分辨是哪只猫咪。志愿者尝试用网兜、布条、麻袋等营救猫咪，均未成功。当时天色已晚，志愿者只好作罢。\n\n第二天，理毛联系老师向学校相关部门求助。工作人员对现场情况分析后，决定拆窗营救。\n进入猫咪被困的管道里后发现，被困猫咪居然是大名鼎鼎的无德！无德此时已经被吓坏了，不知道在通风管里被困了多久，甚至还被吓尿了。\n但是为了无德潇洒帅猫的人设，理毛并未将此事告诉无德的迷妹们。\n\n得救的无德在短暂休息后再次消失在了大家的视野里，希望无德以后不要再做这么失德的事情了，否则理毛花再多的钱也难以公关掉无德的黑料了。\n\n\n\n在此理毛向三位协助的工人师傅表示衷心的感谢，他们分别是：倪建军、胡思宇和杨运义，感谢他们对弱小生命的爱护与协助！\n");
        animalKnowledgeList.add(animalKnowledge6);

        AnimalKnowledge animalKnowledge7 = new AnimalKnowledge();
        animalKnowledge7.setImgResId(R.drawable.pic_animal_knowledge7);
        animalKnowledge7.setTitle("如果你打算收养流浪猫，该做哪些准备？");
        animalKnowledge7.setContent("在这个天气下，很多养猫的铲屎官都会对外面的流浪猫心生怜悯。\n\n每次看到流浪猫在垃圾桶周围寻找食物，他们就忍不住拿出来一些猫粮喂食流浪猫。\n\n有条件的人，会选择收养流浪猫。\n\n可是，并不是每一只流浪猫都适合收养。\n\n也有一部分流浪猫，它们从小就生活在外面，没有安全感，对人类有着强烈的敌意。\n\n一旦有人靠近，猫咪就会强烈地反抗，甚至有攻击人的行为。\n\n这样的猫咪，真的很难收养。\n\n要知道，即便是家养的宠物猫，也没有完全被人类驯服，更何况一只从小居无定所的流浪猫呢？第一：最好收养幼猫\n\n幼猫的发育还不够成熟，你有大把的时间让幼猫信任你，逐渐熟悉被你圈养在家里的感觉。\n\n而作为一只成年的流浪猫，它们很难对你建立起信任度。\n\n第二：收养流浪猫，最好有足够的心理准备\n\n如果你有养猫的经验和经历，物质方面是不需要担心的；但你需要做好心理上的准备。\n\n流浪猫可能会攻击人，可能会不信任你，可能在家里闯祸。\n\n如果你家里已经养了猫，那么流浪猫很可能无法与你家猫“好好相处”。\n\n这些都是你应该考虑到的问题。\n\n\n当然，你居住的空间应该足够大。\n\n因为流浪猫刚刚被圈养在家里，往往缺少安全感，害怕，会找个地方躲起来。\n\n它们的应激反应一旦严重，可能是失控的状态，这些都需要你做好准备。\n\n如果你没有养宠物的经验，千万不要轻易收养流浪猫，因为你很难跟它们好好相处。\n\n喜欢宠物，想要给流浪猫一些帮助，这是一个人善良的表现。\n\n但善良，应该建立在有足够准备的前提下。");
        animalKnowledgeList.add(animalKnowledge7);

        AnimalKnowledge animalKnowledge8 = new AnimalKnowledge();
        animalKnowledge8.setImgResId(R.drawable.pic_animal_knowledge8);
        animalKnowledge8.setTitle("饭饭妈，你的孩子丢了！");
        animalKnowledge8.setContent("饭饭妈是一只小小的狸花猫，因为的她的女儿饭饭非常可爱，像一只活泼的小狗，所以大家都叫她饭饭妈。\n话说饭饭妈在寒假期间又怀孕了，开学后没多久，饭饭妈就一连生下了八只小猫。\n饭饭妈尽职尽责，只要有空就出来找吃的，回去给八只小奶猫喂奶。但是因为饭饭妈性格胆小，不太亲人，总是吃不到太多，整个猫都饿瘦了。\n\n过了几天后，理毛志愿者发现饭饭妈把孩子藏起来了！但是粗心的饭饭妈丢下了一只黑色小猫，其余的七只都不知所踪。\n志愿者只好暂时收留了这只可爱的小猫，照顾她。收留奶猫的志愿者同时也收留了饭饭，看到自己的亲妹妹饭饭一脸震惊。\n饭饭妈尽职尽责，只要有空就出来找吃的，回去给八只小奶猫喂奶。但是因为饭饭妈性格胆小，不太亲人，总是吃不到太多，整个猫都饿瘦了。\n\n过了几天后，理毛志愿者发现饭饭妈把孩子藏起来了！但是粗心的饭饭妈丢下了一只黑色小猫，其余的七只都不知所踪。\n志愿者只好暂时收留了这只可爱的小猫，照顾她。收留奶猫的志愿者同时也收留了饭饭，看到自己的亲妹妹饭饭一脸震惊。\n");
        animalKnowledgeList.add(animalKnowledge8);

        return animalKnowledgeList;

    }
}
