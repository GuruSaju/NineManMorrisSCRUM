'''
Created on Oct 3, 2015

@author: speng
'''
from Game_board import Game_board
from Player import Player
from Game_state import Game_state
class Game_controller(object):
    '''
    classdocs
    '''
    def __init__(self):
        '''
        Constructor
        '''
        self.player_1 = Player("player_1", "X")
        self.player_2 = Player("player_2", "O")
        self.last_pos = None
        self.current_player = self.player_1
        self.state = Game_state.drop
        self.board = Game_board()
        
    
    def move(self, pos):
        if(not self.board.is_legal(self.state, self.current_player, self.last_pos, pos)):
            return False
        #apply change to board
        self.board.change(self.state, self.last_pos, pos)
        #state transition
        if self.state == Game_state.move_1:
            self.last_pos = pos
            self.state = Game_state.move_2
            return True
        elif self.state == Game_state.fly_1:
            self.last_pos = pos
            self.state = Game_state.fly_2
            return True
        elif self.state == Game_state.drop or self.state == Game_state.move_2 or self.state == Game_state.fly_2:      
            if(self.board.is_mill(pos)):
                self.state = Game_state.new_mill
                return True
            # the case did not form a mill
            self.change_player()
            self.decide_new_player_state()
            return True
        else:
        #the case state == new_mill
            self.change_player()
            self.decide_new_player_state()
            return True
    
    def change_player(self):
        if self.current_player == self.player_1:
            self.current_player = self.player_2
        else:
            self.current_player = self.player_1
    
    def decide_new_player_state(self):
        if self.current_player.tokens_put < 9:
            self.state = Game_state.drop
        elif self.current_player.tokens_left < 3:
            self.set_opponent_win()
        elif self.current_player.tokens_left == 3:
            self.state = Game_state.fly_1
        else:
            self.state = Game_state.move_1
    
    def set_opponent_win(self):
        if self.current_player == self.player_1:
            self.state = Game_state.player_2_win
        else:
            self.state = Game_state.player_1_win
    
