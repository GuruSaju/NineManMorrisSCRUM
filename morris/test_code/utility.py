'''
Created on Oct 4, 2015

@author: speng
'''
import re

def extract_input(pat, replay_file, input_file):
    file_obj = open(replay_file)
    text = file_obj.read()
    file_obj.close()
    print text
    res = re.findall(pat, text)
    for item in res:
        item = tuple([int(digit) for digit in item])
        print item
    res = [str(item) for item in res]
    file_obj = open(input_file,"w")
    file_obj.write("\n".join(res))
    file_obj.write("\n\n")
    file_obj.close()

if __name__ == "__main__":
#     pat = r"\(\s*\d\s*,\s*\d\s*\)"
    pat = r"x\s*=\s*(\d)\s*,\s*y\s*=\s*(\d)"
#     replay_file = "../tests/replay3"
    replay_file = "../tests/bug2 (1)"
    input_file = "../tests/bug_move.in"
    extract_input(pat, replay_file, input_file)