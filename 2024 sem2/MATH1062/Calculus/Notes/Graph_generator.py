import numpy as np
import matplotlib.pyplot as plt
from scipy.integrate import solve_ivp

# Define the differential equation y' = x * y
def f(x, y):
    return x * y

# Define the grid of points for the direction field
x = np.linspace(-2, 2, 20)
y = np.linspace(-2, 2, 20)
X, Y = np.meshgrid(x, y)

# Compute the slope (direction) at each point
U = 1  # Horizontal component (constant for normalization)
V = f(X, Y)  # Vertical component based on the differential equation

# Normalize the direction vectors for better visualization
N = np.sqrt(U**2 + V**2)
U = U / N
V = V / N

# Plot the direction field
plt.figure(figsize=(8, 8))
plt.quiver(X, Y, U, V, color='blue')

# Solve the differential equation for several initial conditions
x_vals = np.linspace(-2, 2, 100)
initial_conditions = [-1, 0.5, 1]

for y0 in initial_conditions:
    sol = solve_ivp(f, [x_vals[0], x_vals[-1]], [y0], t_eval=x_vals)
    plt.plot(sol.t, sol.y[0], label=f'y(0)={y0}')

# Customize and show the plot
plt.title("Direction Field with Solution Curves for y' = x * y")
plt.xlabel('x')
plt.ylabel('y')
plt.grid(True)
plt.legend()

# Save the plot as a PNG file
plt.savefig('Graphs/direction_field.png')



