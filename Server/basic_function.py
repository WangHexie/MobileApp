import hashlib
import random
import time

dic = {}


def create_key():
    hash_string = str(time.time()) + str(random.randrange(99999999))
    final_key = hashlib.sha256(hash_string.encode('utf-8')).hexdigest()
    return final_key


def set_dic():
    with open("dic.txt", "r") as f:
        dic_str = f.read()
    global dic
    dic = eval(dic_str)


def get_dic():
    global dic
    if dic == {}:
        set_dic()
    return dic


def get_single_character(two_hex):  # need to rewrite
    dic = get_dic()
    return dic[two_hex]


def hash_to_chinese_key(hash, lenth=5):
    chinese_key = ""
    for i in range(lenth):
        chinese_key += get_single_character(hash[i:i + 2])
    return chinese_key


if __name__ == '__main__':
    print(hash_to_chinese_key("8bfd6fb7e44396db8033cd6715a25432f1e370a6a9d7a2b6674024d3696baf5c"))