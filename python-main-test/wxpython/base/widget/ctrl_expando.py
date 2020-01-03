import wx
from wx.lib.expando import ExpandoTextCtrl, EVT_ETC_LAYOUT_NEEDED


class MyFrame(wx.Frame):

    def __init__(self):
        wx.Frame.__init__(self, None, title="Test ExpandoTextCtrl")
        self.pnl = p = wx.Panel(self)
        self.eom = ExpandoTextCtrl(p, size=(250, -1),
                                   value="This control will expand as you type")
        self.Bind(EVT_ETC_LAYOUT_NEEDED, self.on_refit, self.eom)

        # create some buttons and sizers to use in testing some
        # features and also the layout
        vBtnSizer = wx.BoxSizer(wx.VERTICAL)

        btn = wx.Button(p, -1, "Write Text")
        self.Bind(wx.EVT_BUTTON, self.on_write_text, btn)
        vBtnSizer.Add(btn, 0, wx.ALL | wx.EXPAND, 5)

        btn = wx.Button(p, -1, "Append Text")
        self.Bind(wx.EVT_BUTTON, self.on_append_text, btn)
        vBtnSizer.Add(btn, 0, wx.ALL | wx.EXPAND, 5)

        sizer = wx.BoxSizer(wx.HORIZONTAL)
        col1 = wx.BoxSizer(wx.VERTICAL)
        col1.Add(self.eom, 0, wx.ALL, 10)
        sizer.Add(col1)
        sizer.Add(vBtnSizer)
        p.SetSizer(sizer)

        # Put the panel in a sizer for the frame so we can use self.Fit()
        frameSizer = wx.BoxSizer()
        frameSizer.Add(p, 1, wx.EXPAND)
        self.SetSizer(frameSizer)

        self.Fit()

    def on_refit(self, evt):
        # The Expando control will redo the layout of the
        # sizer it belongs to, but sometimes this may not be
        # enough, so it will send us this event so we can do any
        # other layout adjustments needed.  In this case we'll
        # just resize the frame to fit the new needs of the sizer.
        self.Fit()

    def on_write_text(self, evt):
        self.eom.WriteText("This is a test...  Only a test.  If this had "
                           "been a real emergency you would have seen the "
                           "quick brown fox jump over the lazy dog.")

    def on_append_text(self, evt):
        self.eom.AppendText("Appended text.")


app = wx.App(0)
frame = MyFrame()
frame.Show()
app.MainLoop()
