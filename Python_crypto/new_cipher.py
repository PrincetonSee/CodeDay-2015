from graphics import *

def getkey(x):

    intSum = 0

    for i in range(len(x)):
        intSum += ord(x[i])

    return (intSum//len(x))

def encrypt(x,k):

    strTran = ''

    for i in range(len(x)):
        temp = ord(x[i])+k
        if temp > 126:
            temp += -94                   
        strTran += chr(temp)
    return strTran



def main():

    while True:
        win = GraphWin("Caesar Cipher", 400,300)
        win.setCoords(0.0,0.0,3.0,4.0)

        Text(Point(1,3.5)," Type in your text: ").draw(win)
        Text(Point(1,3)," Type in your key: ").draw(win)
        Text(Point(1,1)," Here is the encryption: ").draw(win)

        input1 = Entry(Point(2,3.5),27)
        input1.setText('')
        input1.draw(win)

        input2 = Entry(Point(1.6,3),9)
        input2.setText('')
        input2.draw(win)

        output = Text (Point(2,0.5), "")
        output.draw(win)

        button = Text(Point(1.5,2.0), "Encrypt")
        button.draw(win)

        Rectangle(Point(1,1.5), Point(2,2.5)).draw(win)

        win.getMouse()

        strInput = str(input1.getText())

        key = getkey(str(input2.getText()))

        result = encrypt(strInput,key)

        output.setText(result)

        win.getMouse()

        win.close()
    

main()
    
