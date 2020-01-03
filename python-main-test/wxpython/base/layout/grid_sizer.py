import wx


class Example(wx.Frame):

    def __init__(self, parent, title):
        super(Example, self).__init__(parent, title=title)

        self.init_ui()
        self.Centre()

    def init_ui(self):
        menubar = wx.MenuBar()
        fileMenu = wx.Menu()
        menubar.Append(fileMenu, '&File')
        self.SetMenuBar(menubar)

        vbox = wx.BoxSizer(wx.VERTICAL)
        self.display = wx.TextCtrl(self, style=wx.TE_RIGHT)
        vbox.Add(self.display, flag=wx.EXPAND | wx.TOP | wx.BOTTOM, border=4)
        """
        In the constructor we specify the number of rows and columns in the table and the vertical and horizontal space 
        between our cells.
        """
        gs = wx.GridSizer(5, 4, 5, 5)  # wx.GridSizer(int rows=1, int cols=0, int vgap=0, int hgap=0)

        gs.AddMany([(wx.Button(self, label='Cls'), 0, wx.EXPAND),
                    (wx.Button(self, label='Bck'), 0, wx.EXPAND),
                    (wx.StaticText(self), wx.EXPAND),
                    (wx.Button(self, label='Close'), 0, wx.EXPAND),
                    (wx.Button(self, label='7'), 0, wx.EXPAND),
                    (wx.Button(self, label='8'), 0, wx.EXPAND),
                    (wx.Button(self, label='9'), 0, wx.EXPAND),
                    (wx.Button(self, label='/'), 0, wx.EXPAND),
                    (wx.Button(self, label='4'), 0, wx.EXPAND),
                    (wx.Button(self, label='5'), 0, wx.EXPAND),
                    (wx.Button(self, label='6'), 0, wx.EXPAND),
                    (wx.Button(self, label='*'), 0, wx.EXPAND),
                    (wx.Button(self, label='1'), 0, wx.EXPAND),
                    (wx.Button(self, label='2'), 0, wx.EXPAND),
                    (wx.Button(self, label='3'), 0, wx.EXPAND),
                    (wx.Button(self, label='-'), 0, wx.EXPAND),
                    (wx.Button(self, label='0'), 0, wx.EXPAND),
                    (wx.Button(self, label='.'), 0, wx.EXPAND),
                    (wx.Button(self, label='='), 0, wx.EXPAND),
                    (wx.Button(self, label='+'), 0, wx.EXPAND)])

        vbox.Add(gs, proportion=1, flag=wx.EXPAND)
        self.SetSizer(vbox)


def main():
    app = wx.App()
    ex = Example(None, title='Calculator')
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
