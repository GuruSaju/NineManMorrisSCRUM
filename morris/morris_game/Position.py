'''
Created on Oct 3, 2015

@author: speng
'''

class Position(object):
    '''
    classdocs
    '''
    

    def __init__(self, pos, player):
        '''
        Constructor
        '''
        self.coord = pos
        self.player = player
        self.up = None
        self.down = None
        self.left = None
        self.right = None