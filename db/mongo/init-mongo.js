db.createUser({
    user: 'root',
    pwd: 'toor',
    roles: [
        {
            role: 'readWrite',
            db: 'testDB',
        },
    ],
});
db.createCollection('app_users', { capped: false });

db.app_users.insert([
{ 
    "username": "ragnar777", 
    "dni": "VIKI771012HMCRG093", 
    "enabled": true, 
    "password": "$2a$10$JoYLdzpLQB.3jnZNe7lfhOaidsdP6bAbQufTUexLB.cYnm7BudpOS", 
    "role": 
    {
        "granted_authorities": ["ROLE_USER"]
    } 
},
{ 
    "username": "heisenberg", 
    "dni": "BBMB771012HMCRR022", 
    "enabled": true, 
    "password": "$2a$10$yai22sTIjleNYjNklyF80uObQmYwMXp5RMwnVVIUAvMhoC9mm9RmG", 
    "role": 
    {
        "granted_authorities": ["ROLE_USER"]
    } 
},
{ 
    "username": "misterX", 
    "dni": "GOTW771012HMRGR087", 
    "enabled": true, 
    "password": "$2a$10$D3MukYoNTPFM8D1tQiHRSOjnqa/oJdXDlNKay3ri/FOFlOumqYF1q", 
    "role": 
    {
        "granted_authorities": ["ROLE_USER", "ROLE_ADMIN"]
    } 
},
{ 
    "username": "neverMore", 
    "dni": "WALA771012HCRGR054", 
    "enabled": true, 
    "password": "$2a$10$Nzr1JicsUL1pAgQsLwEu9OYA9Tu5.0Dx/BhJbCoQ2WzgEcQKYh1na", 
    "role": 
    {
        "granted_authorities": ["ROLE_ADMIN"]
    } 
}
]);