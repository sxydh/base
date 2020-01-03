import wx

"""
wx.RadioButton is a widget that allows the user to select a single exclusive choice from a group of options. A group 
of radio buttons is defined by having the first radio button in the group contain the wx.RB_GROUP style. All other 
radio buttons defined after the first radio button with this style flag will be added to the function group of the 
first radio button. Declaring another radio button with the wx.RB_GROUP flag will start a new radio button group.
"""


class Example(wx.Frame):

    def __init__(self, *args, **kw):
        super(Example, self).__init__(*args, **kw)

        self.init_ui()

    def init_ui(self):
        pnl = wx.Panel(self)

        self.rb1 = wx.RadioButton(pnl, label='Value A', pos=(10, 10),
                                  style=wx.RB_GROUP)
        self.rb2 = wx.RadioButton(pnl, label='Value B', pos=(10, 30))
        self.rb3 = wx.RadioButton(pnl, label='Value C', pos=(10, 50))

        self.rb1.Bind(wx.EVT_RADIOBUTTON, self.set_val)
        self.rb2.Bind(wx.EVT_RADIOBUTTON, self.set_val)
        self.rb3.Bind(wx.EVT_RADIOBUTTON, self.set_val)

        self.sb = self.CreateStatusBar(3)

        self.sb.SetStatusText("True", 0)
        self.sb.SetStatusText("False", 1)
        self.sb.SetStatusText("False", 2)

        self.SetSize((210, 210))
        self.SetTitle('wx.RadioButton')
        self.Centre()
        self.Show(True)

    def set_val(self, e):
        state1 = str(self.rb1.GetValue())
        state2 = str(self.rb2.GetValue())
        state3 = str(self.rb3.GetValue())

        self.sb.SetStatusText(state1, 0)
        self.sb.SetStatusText(state2, 1)
        self.sb.SetStatusText(state3, 2)


def main():
    ex = wx.App()
    Example(None)
    ex.MainLoop()


if __name__ == '__main__':
    main()
