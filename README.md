# menus-cli
Projet TSI-ENSG CI/CD

# Installation
```bash
git clone https://github.com/JulesPierrat/menus-cli.git
cd menu-cli
mvn clean package
```

# Utilisation
```bash
source execute.sh

# List all the menu on a server
menucli list-menus

# Delete a menu by it id
menucli delete-menu xxx xxx xxx ...

# Option
# --server-url="adress:port" Default: localhost:8080

# Ajouter des menus aux serveur :
curl -H "Content-Type: application/json" --data-raw '{\"name\": \"Menu spécial du chef\", \"dishes\": [{\"name\": \"Bananes aux fraises\"},{\"name\": \"Bananes flambées\"}]}' localhost:8080/menus
