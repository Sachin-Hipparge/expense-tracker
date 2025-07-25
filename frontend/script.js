const form = document.getElementById('expenseForm');
const list = document.getElementById('expenseList');

const api = 'https://expense-tracker-backend-sq55.onrender.com/api/expenses';

function fetchExpenses() {
  fetch(api)
    .then(res => res.json())
    .then(data => {
      list.innerHTML = '';
      data.forEach(exp => {
        const li = document.createElement('li');
        li.innerHTML = `
          ${exp.title} - ₹${exp.amount} - ${exp.category} - ${exp.date}
          <button onclick="deleteExpense(${exp.id})">Delete</button>
        `;
        list.appendChild(li);
      });
    });
}

form.addEventListener('submit', (e) => {
  e.preventDefault();

  const expense = {
    title: document.getElementById('title').value,
    amount: parseFloat(document.getElementById('amount').value),
    category: document.getElementById('category').value,
    date: document.getElementById('date').value,
  };

  fetch(api, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(expense),
  })
    .then(() => {
      form.reset();
      fetchExpenses();
    });
});

fetchExpenses();

function deleteExpense(id) {
	if (confirm('Are you sure you want to delete this expense?')) {
 	 fetch(`${api}/${id}`, {
   	 method: 'DELETE'
 	 }).then(() => fetchExpenses());
	}
}
