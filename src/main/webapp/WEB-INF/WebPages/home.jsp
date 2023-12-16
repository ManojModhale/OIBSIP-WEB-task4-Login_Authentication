<%@ page import="java.util.List" %>
<%@ page import="com.system.bean.BankAccount" %>

<!DOCTYPE html>
<html>

<head>
    <title>Secured Page</title>
    <style>
        #userData {
            margin-top: 20px;
            border: 1px solid #ccc;
            padding: 10px;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>

<body>
    <p>Welcome, you have successfully logged in!</p>
    <h2>This is Bank Database</h2>

    <div id="userData">
        <table>
            <thead>
                <tr>
                    <th>Account Number</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Available Balance</th>
                </tr>
            </thead>
            <tbody>
                <% List<BankAccount> accounts = (List<BankAccount>) session.getAttribute("accList");
                for (int i = 0; i < accounts.size(); i++) { %>
                    <tr>
                        <td><%= accounts.get(i).getAccNo() %></td>
                        <td><%= accounts.get(i).getFirstName() %></td>
                        <td><%= accounts.get(i).getLastName() %></td>
                        <td><%= accounts.get(i).getBalance() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>

</html>
