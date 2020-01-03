import wx

"""
wx.ComboBox is a combination of a single line text field, a button with a down arrow image and a listbox. When you press 
the button, a listbox appears. A user can select only one option from the supplied string list.
"""


class Example(wx.Frame):

    def __init__(self, *args, **kw):
        super(Example, self).__init__(*args, **kw)

        self.init_ui()

    def init_ui(self):
        pnl = wx.Panel(self)

        distros = ['Ubuntu', 'Arch', 'Fedora', 'Debian', 'Mint']
        cb = wx.ComboBox(pnl, pos=(50, 30), choices=distros,
                         style=wx.CB_READONLY)

        self.st = wx.StaticText(pnl, label='', pos=(50, 140))
        cb.Bind(wx.EVT_COMBOBOX, self.on_select)

        self.SetSize((250, 230))
        self.SetTitle('wx.ComboBox')
        self.Centre()
        self.Show(True)

    def on_select(self, e):
        i = e.GetString()
        self.st.SetLabel(i)


def main():
    ex = wx.App()
    Example(None)
    ex.MainLoop()


if __name__ == '__main__':
    main()
