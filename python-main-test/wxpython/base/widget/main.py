import wx

app = wx.App()

"""
Here we create a wx.Frame object. A wx.Frame widget is an important container widget. 
The wx.Frame widget is a parent widget for other widgets. It has no parent itself. 
If we specify None for a parent parameter we indicate that our widget has no parents. 
It is a top widget in the hierarchy of widgets. After we create the wx.Frame widget, 
we must call the Show() method to actually display it on the screen.
"""
frame = wx.Frame(None, title='Simple application')
frame.Show()

"""
The mainloop is an endless cycle. It catches and dispatches all events that exist during the life of our application.
"""
app.MainLoop()
