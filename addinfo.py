#-*- coding: UTF-8 -*- 
import pymysql
from selenium import webdriver
import time
from bs4 import BeautifulSoup
import re
from urllib.request import urlopen
import sys

#检测表里是否有新成员产生，是or否，若是，将新成员的信息传给下一个函数
# def checkNew():
#     #插入语句
#     #一天检查一次是否有新成员产生
#     sql_1='SELECT * FROM teacher WHERE TO_DAYS(now()) - TO_DAYS(created_at) <= 1;'
#     cur.execute(sql_1)
#     conn.commit()
#     # 获取数据方法不变
#     rows = cur.fetchall()
#     # 遍历数据也不变（比上一个更直接一点）
#     for row in rows:
#     # 这里，可以使用键值对的方法，由键名字来获取数据
#         print("%s %s" % (row["realname"], row["workspace"]))
#     return rows



#根据上一个函数返回的信息，在网页上进行查找
def  getInfoPage(userName,workpace):
    driver = webdriver.PhantomJS("F:/Project/pycham/phantomjs/bin/phantomjs.exe")
    driver.get('http://kns.cnki.net/kns/brief/result.aspx?dbprefix=scdb')
    #print(userName,workpace)
    if userName != "":
        driver.find_element_by_id("au_1_value1").send_keys(userName)
    else:
        return ""
    if workpace != "":
         driver.find_element_by_id("au_1_value2").send_keys(workpace)
    driver.find_element_by_id("btnSearch").click()
    time.sleep(10)
    driver.switch_to.frame("iframeResult")
    a=driver.page_source
    return a

#根据上一个函数返回的信息，则开始爬取与他有关的信息，存入数据库里
#得到网址
def GetLinkCode(pageHtml):
    soup = BeautifulSoup(pageHtml,'html.parser')
    link=soup.find_all("a", class_="fz14")
    return link

#切割网址
def GetLink(links):
    paperLinks=[]

    for link in links:
        fileName = re.search('FileName=[^\s&]*', str(link)).group()
        dbName = re.search('DbName=[^\s&]*', str(link)).group()
        shortUrl = fileName+'&'+ dbName
        url = 'http://kns.cnki.net/KCMS/detail/detail.aspx?'+shortUrl
        paperLinks.append(url)#与该老师有关的文章链接存入列表里
    return paperLinks

#将列表里的信息，存入数据库
def SaveSQL(paperLinks,Teaname):
    infos = []
    str=''
    k = 0
    for i in paperLinks:
        html = urlopen(i)
        bs0bj = BeautifulSoup(html,"html.parser")
        title = bs0bj.find("h2",class_='title').text
        fund = bs0bj.find_all("div",{'class':'wxBaseinfo'})[0].find_all('p')[1].text
        keyWord = bs0bj.find_all("div",{'class':'wxBaseinfo'})[0].find_all('p')[2].text
        info=title+fund+keyWord
        infos.append(info)
        str+= infos[k]
        k=k+1
        if(k>=len(paperLinks)):
            break
    sql_2 = 'update userinformation set info=%s where realname=%s;'
    cur.execute(sql_2,[str,Teaname])
    conn.commit()

def addmain(realname,workspace):

	getHtml = getInfoPage(realname, workspace)  # 得到与老师相关的检索页面的源码
	links = GetLinkCode(getHtml)
	url = GetLink(links)
	SaveSQL(url, realname)

# news=[]
# news=checkNew()
# if(news==''):
#     print('没有新成员！')
# else:
#     for new in news:
#         getHtml = getInfoPage(new["Tname"], new["workspace"])  # 得到与老师相关的检索页面的源码
#         links = GetLinkCode(getHtml)
#         url = GetLink(links)
#         SaveSQL(url, new["Tname"])


conn= pymysql.connect(
	host='localhost',
	port=3306,
	user='root',
	passwd='0503',
	db='retrievalsystemdb',
	charset='utf8',
	cursorclass=pymysql.cursors.DictCursor,
	)
cur = conn.cursor()
realname=sys.argv[1]
workspace=sys.argv[2]
addmain(realname,workspace)
cur.close()
conn.close()
