'''
Created on Oct 3, 2015

@author: speng
'''
from Position import Position
from Game_state import Game_state
class Game_board(object):
    '''
    classdocs
    '''

    __coord_list = [(0,0), (0,3), (0,6),
                    (1,1), (1,3), (1,5),
                    (2,2), (2,3), (2,4),
                    (3,0), (3,1), (3,2), (3,4), (3,5), (3,6),
                    (4,2), (4,3), (4,4),
                    (5,1), (5,3), (5,5),
                    (6,0), (6,3), (6,6)]
    def __init__(self):
        '''
        Constructor
        '''
        self.positions = {}
        for coord in self.__coord_list:
            self.positions[coord] = Position(coord, None)
        self.init()
    
    def is_legal(self, state, player, last_pos, pos):
        '''last_pos is the position selected in move_1 or fly_1
        state is an instance of Game_state
        player is instance of Play'''
        if(pos.coord not in self.__coord_list):
            return False
        if(state == Game_state.drop or state == Game_state.fly_2):
            return self.is_neutral_pos(pos)
        if(state == Game_state.move_1):
            return self.is_same_player(pos) and self.has_vacant_neibour(pos)
        if(state == Game_state.fly_1):
            return self.is_same_player(pos)
        if(state == Game_state.move_2):
            return self.is_neutral_pos(pos) and self.is_adjecent_to_selected(last_pos, pos)
        if(state == Game_state.new_mill):
            return self.is_opponent_pos(pos)
    
    def change(self, state, last_pos, pos):
        if(state == Game_state.drop):
            self.put_token(pos)
        elif(state == Game_state.move_2 or state == Game_state.fly_2):
            self.remove_token(self.positions[last_pos.coord])
            self.put_token(pos)
        elif(state == Game_state.new_mill):
            self.remove_token(pos)
            
    def remove_token(self, pos):
        self.positions[pos.coord].player.remove_token()
        self.positions[pos.coord].player = None
    
    def put_token(self, pos):
        self.positions[pos.coord].player = pos.player
        pos.player.put_token()
    
    def is_mill(self, pos):
        #check whether it can form a mill
        return self.is_vertical_mill(pos) or self.is_horizontal_mill(pos)
        
        return False
    def is_vertical_mill(self, pos):
        ret = False
        corre_pos = self.positions[pos.coord]
        this_player = pos.player
        if corre_pos.up == None:
            ret = corre_pos.down.player == this_player and corre_pos.down.down.player == this_player
        elif corre_pos.down == None:
            ret = corre_pos.up.player == this_player and corre_pos.up.up.player == this_player
        else:
            ret = corre_pos.down.player == this_player and corre_pos.up.player == this_player
        return ret
    
    def is_horizontal_mill(self, pos):
        ret = False
        corre_pos = self.positions[pos.coord]
        this_player = pos.player
        if corre_pos.left == None:
            ret = corre_pos.right.player == this_player and corre_pos.right.right.player == this_player
        elif corre_pos.right == None:
            ret = corre_pos.left.player == this_player and corre_pos.left.left.player == this_player
        else:
            ret = corre_pos.right.player == this_player and corre_pos.left.player == this_player
        return ret
    
    
    def is_neutral_pos(self, pos):
        return self.positions[pos.coord].player == None
    
    def is_opponent_pos(self, pos):
        return not self.is_same_player(pos) and not self.is_neutral_pos(pos)
    
    def is_same_player(self, pos):
        return self.positions[pos.coord].player == pos.player
        
    def is_adjecent_to_selected(self, last_pos, pos):
        #check if is adjacent to last selected token
        corre_pos = self.positions[pos.coord]
        corre_last_pos = self.positions[last_pos.coord]
        return (corre_pos == corre_last_pos.up      or 
                corre_pos == corre_last_pos.down    or 
                corre_pos == corre_last_pos.left    or 
                corre_pos == corre_last_pos.right)
    
    def has_vacant_neibour(self, pos):
        corre_pos = self.positions[pos.coord]
        return (corre_pos.up    is not None and corre_pos.up.player     is None or
                corre_pos.down  is not None and corre_pos.down.player   is None or
                corre_pos.left  is not None and corre_pos.left.player   is None or
                corre_pos.right is not None and corre_pos.right.player  is None)
    
    def __str__(self, *args, **kwargs):
        #node_list = [pos.player.color if pos.player is not None else "*" for coord in sorted(self.positions, key=self.positions.get)]
        node_list = []
        for coord in self.__coord_list:
            if(self.positions[coord].player == None):
                node_list.append("*")
            else:
                node_list.append(self.positions[coord].player.color)
        
        board_repr = """
          0 1 2 3 4 5 6 
        0 {}-----{}-----{}
        1 | {}---{}---{} |
        2 | | {}-{}-{} | |
        3 {}-{}-{}   {}-{}-{}
        4 | | {}-{}-{} | |
        5 | {}---{}---{}-|
        6 {}-----{}-----{}""".format(*node_list)
        return board_repr
    
    def init(self):
        self.positions[(0, 0)].down     = self.positions[(3, 0)]
        self.positions[(0, 0)].right    = self.positions[(0, 3)]
        
        self.positions[(0, 3)].down     = self.positions[(1, 3)]
        self.positions[(0, 3)].left     = self.positions[(0, 0)]
        self.positions[(0, 3)].right    = self.positions[(0, 6)]
        
        self.positions[(0, 6)].down     = self.positions[(3, 6)]
        self.positions[(0, 6)].left     = self.positions[(0, 3)]
        
        self.positions[(1, 1)].down     = self.positions[(3, 1)]
        self.positions[(1, 1)].right    = self.positions[(1, 3)]
        
        self.positions[(1, 3)].up       = self.positions[(0, 3)]
        self.positions[(1, 3)].down     = self.positions[(2, 3)]
        self.positions[(1, 3)].left     = self.positions[(1, 1)]
        self.positions[(1, 3)].right    = self.positions[(1, 5)]
        
        self.positions[(1, 5)].down     = self.positions[(3, 5)]
        self.positions[(1, 5)].left     = self.positions[(1, 3)]
        
        self.positions[(2, 2)].down     = self.positions[(3, 2)]
        self.positions[(2, 2)].right    = self.positions[(2, 3)]
        
        self.positions[(2, 3)].up       = self.positions[(1, 3)]
        self.positions[(2, 3)].left     = self.positions[(2, 2)]
        self.positions[(2, 3)].right    = self.positions[(2, 4)]
        
        self.positions[(2, 4)].down     = self.positions[(3, 4)]
        self.positions[(2, 4)].left     = self.positions[(2, 3)]
        
        self.positions[(3, 0)].up       = self.positions[(0, 0)]
        self.positions[(3, 0)].down     = self.positions[(6, 0)]
        self.positions[(3, 0)].right    = self.positions[(3, 1)]
        
        self.positions[(3, 1)].up       = self.positions[(1, 1)]
        self.positions[(3, 1)].down     = self.positions[(5, 1)]
        self.positions[(3, 1)].left     = self.positions[(3, 0)]
        self.positions[(3, 1)].right    = self.positions[(3, 2)]
        
        self.positions[(3, 2)].up       = self.positions[(2, 2)]
        self.positions[(3, 2)].down     = self.positions[(4, 2)]
        self.positions[(3, 2)].left     = self.positions[(3, 1)]
        
        self.positions[(3, 4)].up       = self.positions[(2, 4)]
        self.positions[(3, 4)].down     = self.positions[(4, 4)]
        self.positions[(3, 4)].right    = self.positions[(3, 5)]
        
        self.positions[(3, 5)].up       = self.positions[(1, 5)]
        self.positions[(3, 5)].down     = self.positions[(5, 5)]
        self.positions[(3, 5)].left     = self.positions[(3, 4)]
        self.positions[(3, 5)].right    = self.positions[(3, 6)]
        
        self.positions[(3, 6)].up       = self.positions[(0, 6)]
        self.positions[(3, 6)].down     = self.positions[(6, 6)]
        self.positions[(3, 6)].left     = self.positions[(3, 5)]
        
        self.positions[(4, 2)].up       = self.positions[(3, 2)]
        self.positions[(4, 2)].right    = self.positions[(4, 3)]
        
        self.positions[(4, 3)].down     = self.positions[(5, 3)]
        self.positions[(4, 3)].left     = self.positions[(4, 2)]
        self.positions[(4, 3)].right    = self.positions[(4, 4)]
        
        self.positions[(4, 4)].up       = self.positions[(3, 4)]
        self.positions[(4, 4)].left     = self.positions[(4, 3)]
        
        self.positions[(5, 1)].up       = self.positions[(3, 1)]
        self.positions[(5, 1)].right    = self.positions[(5, 3)]
        
        self.positions[(5, 3)].up       = self.positions[(4, 3)]
        self.positions[(5, 3)].down     = self.positions[(6, 3)]
        self.positions[(5, 3)].left     = self.positions[(5, 1)]
        self.positions[(5, 3)].right    = self.positions[(5, 5)]
        
        self.positions[(5, 5)].up       = self.positions[(3, 5)]
        self.positions[(5, 5)].left     = self.positions[(5, 3)]
        
        self.positions[(6, 0)].up       = self.positions[(3, 0)]
        self.positions[(6, 0)].right    = self.positions[(6, 3)]
        
        self.positions[(6, 3)].up       = self.positions[(5, 3)]
        self.positions[(6, 3)].left     = self.positions[(6, 0)]
        self.positions[(6, 3)].right    = self.positions[(6, 6)]
        
        self.positions[(6, 6)].up       = self.positions[(3, 6)]
        self.positions[(6, 6)].left     = self.positions[(6, 3)]
    
        