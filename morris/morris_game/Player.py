'''
Created on Oct 3, 2015

@author: speng
'''

class Player(object):

    def __init__(self, name, color):
        '''
        Constructor
        '''
        self.tokens_put = 0
        self.tokens_left = 0   
        self.name = name
        self.color = color
    
    def __str__(self, *args, **kwargs):
        return "{player} {color}".format(player = self.name, color = self.color) 
    
    def put_token(self):
        self.tokens_put = self.tokens_put +1
        self.tokens_left = self.tokens_left +1
        
    def remove_token(self):
        self.tokens_left = self.tokens_left -1