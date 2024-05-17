using CSDB.classes;
using CSDB.tables;
using MySql.Data.MySqlClient;

MySqlConnection mySqlConnection = new MySqlConnection("server=127.0.0.1;uid=root;pwd=pisica65;database=musicapp");
mySqlConnection.Open();

Page page = new MainPage();

string input;

while(true)
{
    Console.Clear();
    page.print();
    input = Console.ReadLine();
    page = page.afterInput(input);
}