#!/usr/bin/env python
'''
Created on Oct 3, 2015

@author: speng
'''
# from morris_game.Game_controller import Game_controller, Game_state
import re
from Game_controller import Game_controller
from Game_controller import Game_state
from Position import Position
# from morris_game.Position import Position

def parse_input(user_input):
    pat = r"\(\s*\d\s*,\s*\d\s*\)"
    match = re.search(pat, user_input)
    if match is not None:
        return eval(match.group())
    return None

def get_input_coord():
    #coord = input("enter a postion in format (x,y)\n")
    user_input = raw_input("enter a postion in format (x,y)\n")
    coord = parse_input(user_input)
    while coord is None:
        print "invalid input"
        user_input = raw_input("enter a postion in format (x,y)\n")
        coord = parse_input(user_input)
    return coord

if __name__ == '__main__':
    game = Game_controller()
    AI = False
    AI_player = game.player_2
    while(game.state != Game_state.player_1_win and game.state != Game_state.player_2_win):
        print game.board
        print game.current_player
        print game.state
        if AI == True and game.current_player == AI_player:
            #randomly generate a coordinate
            pass
        else:
            coord = get_input_coord()
            pos = Position(coord,game.current_player)
        if(not game.move(pos)):
            print "illegal move"
    print game.board
    print game.state
    
        