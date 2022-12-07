package com.ablaze.ChiChiCampusFinance.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteHelper extends SQLiteOpenHelper {

    public MySqliteHelper(Context context) {
        // 版本号大于一执行，下次执行修改版本号
        super(context, "assetsDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /* 创建数据库 */
        db.execSQL("create table tb_assets " +
                "(id integer primary key autoincrement," +
                "assetsName varchar(50)," +
                "assetsType varchar(50)," +
                "assetsMoney varchar(50)," +
                "Remarks varchar(100)," +
                "username varchar(50))");
        db.execSQL("create table tb_account " +
                "(id integer primary key autoincrement," +
                "accountMoney varchar(50)," +
                "accountType varchar(50)," +
                "payType varchar(50)," +
                "assetsName varchar(50)," +
                "time varchar(50)," +
                "Remarks varchar(100)," +
                "username varchar(50))");
        db.execSQL("create table tb_budget " +
                "(id integer primary key autoincrement," +
                "budgetMoney varchar(50)," +
                "accountType varchar(50)," +
                "assetsName varchar(50)," +
                "Remarks varchar(100)," +
                "username varchar(50))");
        db.execSQL("create table tb_fund " +
                "(id integer primary key autoincrement," +
                "fundName varchar(50)," +
                "rate varchar(50)," +
                "joined varchar(50)," +
                "Remarks varchar(100))");
        db.execSQL("create table tb_work_study " +
                "(id integer primary key autoincrement," +
                "workName varchar(50)," +
                "dailySalary varchar(50)," +
                "telephone varchar(50)," +
                "place varchar(50)," +
                "Remarks varchar(100))");

        db.execSQL("insert into tb_fund(fundName, rate, joined, Remarks)" + "values('广发道琼斯美国石油指数(QDII-LOF)A', '+3.50%', '未购买', '本基金的投资范围为具有良好流动性的金融工具,包括国内依法发行上市的股票(包括创业板及其他依法发行、上市的股票、存托凭证)、港股通标的股票、债券(包括国内依法发行和上市交易的国债、地方政府债、金融债、企业债、公司债、次级债、可转换债券、分离交易可转债、可交换债券、央行票据、中期票据、短期融资券(包括超短期融资券)等)、资产支持证券、债券回购、同业存单、银行存款、货币市场工具、股指期货、国债期货以及法律法规或中国证监会允许基金投资的其他金融工具(但须符合中国证监会相关规定)。 如法律法规或监管机构以后允许基金投资其他品种,基金管理人在履行适当程序后,可以将其纳入投资范围,并可依据届时有效的法律法规适时合理地调整投资范围。')");
        db.execSQL("insert into tb_fund(fundName, rate, joined, Remarks)" + "values('红土创新新科技股票（006265）', '+3.83%', '未购买', '本基金的投资范围为具有良好流动性的金融工具,包括国内依法发行上市的股票(包括中小板、创业板以及其他经中国证监会核准上市的股票)、债券(包括国债、金融债、企业债、公司债、次级债、可转换债券、分离交易可转债、央行票据、中期票据、短期融资券(含超短期融资券)、可交换债券、中小企业私募债券等)、衍生工具(权证、股指期货等)、资产支持证券、债券回购、银行存款以及经中国证监会批准允许基金投资的其他金融工具(但需符合中国证监会的相关规定)。')");
        db.execSQL("insert into tb_fund(fundName, rate, joined, Remarks)" + "values('南方大数据100指数A（001113）', '+2.93%', '未购买', '本基金主要投资于标的指数成份股、备选成份股。为更好地实现投资目标,本基金可少量投资于非成份股(包括中小板、创业板及其他经中国证监会核准上市的股票)、债券(包括国内依法发行和上市交易的国债、央行票据、金融债券、企业债券、公司债券、中期票据、短期融资券、超短期融资券、次级债券、政府支持机构债、政府支持债券、地方政府债、可转换债券及其他经中国证监会允许投资的债券)、资产支持证券、债券回购、银行存款(包括协议存款、定期存款及其他银行存款)、货币市场工具、权证、股指期货以及经中国证监会允许基金投资的其他金融工具,但需符合中国证监会的相关规定。')");
        db.execSQL("insert into tb_fund(fundName, rate, joined, Remarks)" + "values('南方大数据100指数C（004344）', '+2.92%', '未购买', '本基金主要投资于标的指数成份股、备选成份股。为更好地实现投资目标,本基金可少量投资于非成份股(包括中小板、创业板及其他经中国证监会核准上市的股票)、债券(包括国内依法发行和上市交易的国债、央行票据、金融债券、企业债券、公司债券、中期票据、短期融资券、超短期融资券、次级债券、政府支持机构债、政府支持债券、地方政府债、可转换债券及其他经中国证监会允许投资的债券)、资产支持证券、债券回购、银行存款(包括协议存款、定期存款及其他银行存款)、货币市场工具、权证、股指期货以及经中国证监会允许基金投资的其他金融工具,但需符合中国证监会的相关规定。')");
        db.execSQL("insert into tb_fund(fundName, rate, joined, Remarks)" + "values('嘉实清洁能源股票发起式A（017073）', '+2.67%', '未购买', '本基金投资于国内依法发行上市的股票(包含创业板、存托凭证及其他依法发行上市的股票)、内地与香港股票市场交易互联互通机制下允许买卖的香港联合交易所上市股票(以下简称“港股通标的股票”)、债券(国债、地方政府债、金融债、企业债、公司债、次级债、可转换债券(含分离交易可转债)、可交换公司债券、央行票据、短期融资券、超短期融资券、中期票据等)、资产支持证券、债券回购、同业存单、银行存款、股指期货、国债期货、股票期权、现金以及法律法规或中国证监会允许基金投资的其他金融工具(但须符合中国证监会的相关规定)。 本基金可根据相关法律法规和基金合同的约定,参与融资业务。 如法律法规或监管机构以后允许基金投资其他品种,基金管理人在履行适当程序后,可以将其纳入投资范围。')");
        db.execSQL("insert into tb_fund(fundName, rate, joined, Remarks)" + "values('嘉实清洁能源股票发起式C（017074）', '+2.65%', '未购买', '本基金投资于国内依法发行上市的股票(包含创业板、存托凭证及其他依法发行上市的股票)、内地与香港股票市场交易互联互通机制下允许买卖的香港联合交易所上市股票(以下简称“港股通标的股票”)、债券(国债、地方政府债、金融债、企业债、公司债、次级债、可转换债券(含分离交易可转债)、可交换公司债券、央行票据、短期融资券、超短期融资券、中期票据等)、资产支持证券、债券回购、同业存单、银行存款、股指期货、国债期货、股票期权、现金以及法律法规或中国证监会允许基金投资的其他金融工具(但须符合中国证监会的相关规定)。 本基金可根据相关法律法规和基金合同的约定,参与融资业务。 如法律法规或监管机构以后允许基金投资其他品种,基金管理人在履行适当程序后,可以将其纳入投资范围。')");
        db.execSQL("insert into tb_fund(fundName, rate, joined, Remarks)" + "values('广发先进制造股票A（014191）', '+2.23%', '未购买', '本基金的投资范围为具有良好流动性的金融工具,包括国内依法发行上市的股票(包括创业板及其他依法发行、上市的股票、存托凭证)、港股通标的股票、债券(包括国内依法发行和上市交易的国债、地方政府债、金融债、企业债、公司债、次级债、可转换债券、分离交易可转债、可交换债券、央行票据、中期票据、短期融资券(包括超短期融资券)等)、资产支持证券、债券回购、同业存单、银行存款、货币市场工具、股指期货、国债期货以及法律法规或中国证监会允许基金投资的其他金融工具(但须符合中国证监会相关规定)。 如法律法规或监管机构以后允许基金投资其他品种,基金管理人在履行适当程序后,可以将其纳入投资范围,并可依据届时有效的法律法规适时合理地调整投资范围。')");
        db.execSQL("insert into tb_fund(fundName, rate, joined, Remarks)" + "values('广发先进制造股票C（014192）', '+2.21%', '未购买', '本基金的投资范围为具有良好流动性的金融工具,包括国内依法发行上市的股票(包括创业板及其他依法发行、上市的股票、存托凭证)、港股通标的股票、债券(包括国内依法发行和上市交易的国债、地方政府债、金融债、企业债、公司债、次级债、可转换债券、分离交易可转债、可交换债券、央行票据、中期票据、短期融资券(包括超短期融资券)等)、资产支持证券、债券回购、同业存单、银行存款、货币市场工具、股指期货、国债期货以及法律法规或中国证监会允许基金投资的其他金融工具(但须符合中国证监会相关规定)。 如法律法规或监管机构以后允许基金投资其他品种,基金管理人在履行适当程序后,可以将其纳入投资范围,并可依据届时有效的法律法规适时合理地调整投资范围。')");
        db.execSQL("insert into tb_fund(fundName, rate, joined, Remarks)" + "values('易方达MSCI互联互通指数A（016498）', '+2.17%', '未购买', '本基金的投资范围包括国内依法发行上市的股票(包括创业板、科创板及其他依法发行上市的股票、存托凭证)、内地与香港股票市场交易互联互通机制允许买卖的香港证券市场股票(以下简称“港股通股票”)、国内依法发行上市的债券(包括国债、央行票据、地方政府债、金融债、企业债、公司债、次级债、中期票据、短期融资券、可转换债券、可交换债券等)、资产支持证券、债券回购、银行存款、同业存单、货币市场工具、股指期货、国债期货、股票期权及法律法规或中国证监会允许基金投资的其他金融工具。 如法律法规或监管机构以后允许基金投资其他品种,基金管理人在履行适当程序后,本基金可以将其纳入投资范围。')");
        db.execSQL("insert into tb_fund(fundName, rate, joined, Remarks)" + "values('加入自选国泰医药健康股票C（011326）', '+1.90%', '未购买', '本基金的投资范围为具有良好流动性的金融工具,包括国内依法发行上市的股票(包括中小板、创业板及其他中国证监会允许基金投资的股票)、港股通标的股票、债券(包括国债、地方政府债、央行票据、金融债、企业债、公司债、次级债、可转换债券(含可分离交易可转债)、可交换债券、短期融资券、超短期融资券、中期票据等)、资产支持证券、债券回购、银行存款、同业存单、货币市场工具、股指期货以及法律法规或中国证监会允许基金投资的其他金融工具(但须符合中国证监会相关规定)。')");

        db.execSQL("insert into tb_work_study(workName, dailySalary, telephone, place, Remarks)" + "values('淮南牛肉汤打饭', '30', '19887456871', '乐学餐厅', '必须是在校学生，必须身体健康，没有传染性疾病。工作内容主要是：饭菜售卖、餐桌清理、餐具回收、摘菜等。')");
        db.execSQL("insert into tb_work_study(workName, dailySalary, telephone, place, Remarks)" + "values('刁馋烤肉拌饭结账', '30', '19887456371', '二食堂', '必须是在校学生，必须身体健康，没有传染性疾病。工作内容主要是：饭菜售卖、餐桌清理、餐具回收、摘菜等。')");
        db.execSQL("insert into tb_work_study(workName, dailySalary, telephone, place, Remarks)" + "values('大鱼水煮肉片结账', '30', '19887456871', '乐学餐厅', '必须是在校学生，必须身体健康，没有传染性疾病。工作内容主要是：饭菜售卖、餐桌清理、餐具回收、摘菜等。我院大一、大二全体同学（家庭经济困难或有勤工俭学需求的同学优先）')");
        db.execSQL("insert into tb_work_study(workName, dailySalary, telephone, place, Remarks)" + "values('喷喷香小铁板打饭', '30', '19824456871', '乐学餐厅', '必须是在校学生，必须身体健康，没有传染性疾病。工作内容主要是：饭菜售卖、餐桌清理、餐具回收、摘菜等。')");
        db.execSQL("insert into tb_work_study(workName, dailySalary, telephone, place, Remarks)" + "values('东苑馋嘴鱼结账', '30', '19887456871', '东苑食堂', '必须是在校学生，必须身体健康，没有传染性疾病。工作内容主要是：饭菜售卖、餐桌清理、餐具回收、摘菜等。')");
        db.execSQL("insert into tb_work_study(workName, dailySalary, telephone, place, Remarks)" + "values('米粒串炒饭打饭', '34', '19886256871', '东苑食堂', '必须是在校学生，必须身体健康，没有传染性疾病。工作内容主要是：饭菜售卖、餐桌清理、餐具回收、摘菜等。我院大一、大二全体同学（家庭经济困难或有勤工俭学需求的同学优先）')");
        db.execSQL("insert into tb_work_study(workName, dailySalary, telephone, place, Remarks)" + "values('八宝盖饭结账', '30', '19247456871', '二食堂', '必须是在校学生，必须身体健康，没有传染性疾病。工作内容主要是：饭菜售卖、餐桌清理、餐具回收、摘菜等。我院大一、大二全体同学（家庭经济困难或有勤工俭学需求的同学优先）')");
        db.execSQL("insert into tb_work_study(workName, dailySalary, telephone, place, Remarks)" + "values('打印社打印', '20', '19884556143', '信息楼2楼','负责共享自助打印设备的耗材补充，日常维护，以及相关问题的处理。我院大一、大二全体同学（家庭经济困难或有勤工俭学需求的同学优先）我院大一、大二全体同学（家庭经济困难或有勤工俭学需求的同学优先）')");
        db.execSQL("insert into tb_work_study(workName, dailySalary, telephone, place, Remarks)" + "values('0090炸鸡汉堡', '35', '19887426871', '枫叶餐厅', '必须是在校学生，必须身体健康，没有传染性疾病。工作内容主要是：饭菜售卖、餐桌清理、餐具回收、摘菜等。')");
        db.execSQL("insert into tb_work_study(workName, dailySalary, telephone, place, Remarks)" + "values('黄焖鸡米饭', '30', '19887456437', '西苑餐厅', '必须是在校学生，必须身体健康，没有传染性疾病。工作内容主要是：饭菜售卖、餐桌清理、餐具回收、摘菜等。')");

    }

    // 升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
