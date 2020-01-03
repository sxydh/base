import wx

APP_EXIT = 1


class Example(wx.Frame):

    def __init__(self, *args, **kwargs):
        super(Example, self).__init__(*args, **kwargs)

        self.init_ui()

    def init_ui(self):
        #  First we create a menubar object.
        menubar = wx.MenuBar()
        #  Next we create a menu object.
        fileMenu = wx.Menu()
        """
        We append a menu item into the menu object. 
        The first parameter is the id of the menu item. The standard id will automatically add an icon and a shortcut, 
        Ctrl+Q in our case. 
        The second parameter is the name of the menu item. 
        The last parameter defines the short help string that is displayed on the statusbar, when the menu item is 
        selected. 
        Here we did not create a wx.MenuItem explicitly. It was created by the Append() method behind the scenes. 
        The method returns the created menu item. This reference will be used later to bind an event.
        """
        qmi = wx.MenuItem(fileMenu, APP_EXIT, '&Quit\tCtrl+Q')
        qmi.SetBitmap(wx.Bitmap('exit.png'))
        fileMenu.Append(qmi)
        fileMenu.Append(wx.ID_NEW, '&New')
        fileMenu.Append(wx.ID_OPEN, '&Open')
        fileMenu.Append(wx.ID_SAVE, '&Save')
        fileMenu.AppendSeparator()

        imp = wx.Menu()
        imp.Append(wx.ID_ANY, 'Import newsfeed list...')
        imp.Append(wx.ID_ANY, 'Import bookmarks...')
        imp.Append(wx.ID_ANY, 'Import mail...')

        fileMenu.Append(wx.ID_ANY, 'I&mport', imp)

        self.Bind(wx.EVT_MENU, self.on_quit, id=APP_EXIT)

        menubar.Append(fileMenu, '&File')
        self.SetMenuBar(menubar)

        self.SetSize((350, 250))
        self.SetTitle('Icons and shortcuts')
        self.Centre()

    def on_quit(self, e):
        self.Close()


def main():
    app = wx.App()
    ex = Example(None)
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
