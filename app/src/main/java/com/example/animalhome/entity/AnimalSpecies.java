package com.example.animalhome.entity;

import java.io.Serializable;

/**
 * {
 * "reason":"success",
 * "result":{
 * "list":[
 * {
 * "pettype":1,
 * "name":"哈士奇",
 * "engName":"Siberian Huskiy",
 * "characters":"聪明机灵、极度热情、神经质",
 * "nation":"俄罗斯",
 * "easyOfDisease":"肠胃疾病",
 * "life":"9-15年",
 * "price":"2000-4000元",
 * "desc":"　　西伯利亚",
 * "feature":"　　西伯利亚雪橇犬是一种原始的古老犬种，因它的独特嘶哑的叫声被称之为当今的哈士奇。很早前哈士奇是最原始的交通工具专门拉雪橇的，并用这种狗猎取和饲养驯鹿，或者繁殖这种狗，然后带出他们居住的冻土地，换取温饱。典型狼性犬。",
 * "characterFeature":"　　哈士奇的外表英俊潇洒，精致的五官和丰富的肢体语言充满了奇特的表现，无需复杂的交谈，就能轻易了解他的喜怒哀乐。哈士奇时常会有点神经质。",
 * "careKnowledge":"　　哈士奇虽然看着一副冷漠无情的样子，事实上，哈士奇对人很友好、温柔、热情的。喜欢与人交往是哈士奇的典型性格。通常不表现出护卫犬强烈的领地占有欲，不对陌生人过多的怀疑，也不会攻击其他犬类。因此有很多人喜欢哈士奇。他对主人非常忠诚，一条忠诚的小狗有一个健康的身体是非常重要的。",
 * "feedPoints":"　　哈士奇有着敏感的肠胃，因此在饮食上需要主人多加重视，喂食不当很容易",
 * "url":"http:\/\/www.boqii.com\/entry\/detail\/357.html",
 * "coverURL":"http:\/\/img.boqiicdn.com\/Data\/BK\/P\/imagick14371435571930.png"
 * }
 * ]
 * },
 * "error_code":0
 * }
 */
public class AnimalSpecies implements Serializable {

    private String pettype;
    private String name;
    private String engName;
    private String characters;
    private String nation;
    private String easyOfDisease;
    private String life;
    private String price;
    private String desc;
    private String feature;
    private String characterFeature;
    private String careKnowledge;
    private String feedPoints;
    private String url;
    private String coverURL;

    public String getPettype() {
        return pettype;
    }

    public void setPettype(String pettype) {
        this.pettype = pettype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getEasyOfDisease() {
        return easyOfDisease;
    }

    public void setEasyOfDisease(String easyOfDisease) {
        this.easyOfDisease = easyOfDisease;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getCharacterFeature() {
        return characterFeature;
    }

    public void setCharacterFeature(String characterFeature) {
        this.characterFeature = characterFeature;
    }

    public String getCareKnowledge() {
        return careKnowledge;
    }

    public void setCareKnowledge(String careKnowledge) {
        this.careKnowledge = careKnowledge;
    }

    public String getFeedPoints() {
        return feedPoints;
    }

    public void setFeedPoints(String feedPoints) {
        this.feedPoints = feedPoints;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }
}
