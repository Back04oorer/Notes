import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

# Define the function f(x, y)
def f(x, y):
    return x**2 / 4 + y**2 / 9

# Define the domain of x and y
x = np.linspace(-5, 5, 400)
y = np.linspace(-5, 5, 400)
x, y = np.meshgrid(x, y)
z = f(x, y)

# Plot 1: 3D surface of z = f(x, y)
fig1 = plt.figure(figsize=(6, 6))
ax1 = fig1.add_subplot(111, projection='3d')
ax1.plot_surface(x, y, z, cmap='coolwarm', alpha=0.6)
ax1.set_title(r"$z = f(x, y)$", fontsize=14)
ax1.set_xlabel('x')
ax1.set_ylabel('y')
ax1.set_zlabel('z')
ax1.scatter(0, 0, f(0, 0), color='green', s=100, label='(a, b)')
ax1.legend()
fig1.tight_layout()
fig1.savefig("Graphs/w8_5.png")

# Plot 2: Intersection with plane y=b
b = 1  # Set y = b
fig2 = plt.figure(figsize=(6, 6))
ax2 = fig2.add_subplot(111, projection='3d')
ax2.plot_surface(x, y, z, cmap='coolwarm', alpha=0.6)

# Plot the red intersection line on the surface
x_intersection = np.linspace(-5, 5, 400)
y_intersection = np.full_like(x_intersection, b)  # y = b
z_intersection = f(x_intersection, y_intersection)
ax2.plot(x_intersection, y_intersection, z_intersection, color='red', linewidth=3, label=r'Intersection: $y=b$')

ax2.set_title(r"Intersection with plane $y = b$", fontsize=14)
ax2.set_xlabel('x')
ax2.set_ylabel('y')
ax2.set_zlabel('z')
ax2.legend()
fig2.tight_layout()
fig2.savefig("Graphs/w8_6.png")

# Plot 3: Intersection curve and tangent line
fig3 = plt.figure(figsize=(6, 6))
ax3 = fig3.add_subplot(111)
x_curve = np.linspace(-5, 5, 400)
y_val = b
z_curve = f(x_curve, y_val)

# Draw tangent line at x = 0
x_tangent = np.linspace(-1, 1, 100)
# The derivative of f(x, b) at x = 0 for a parabola is just x/2, so f_x = 0 at x = 0
z_tangent = f(0, y_val) + (x_tangent - 0) * (0 / 2)  # Approximate linear tangent near x = 0

ax3.plot(x_curve, z_curve, label="Intersection Curve (y=b)", color='blue')
ax3.plot(x_tangent, z_tangent, label="Tangent Line", color='red', linestyle='--')
ax3.scatter([0], [f(0, y_val)], color='green', s=100)  # Point (0, f(0, b))
ax3.set_title(r"$f_x = \frac{\mathrm{d}}{\mathrm{d}x}(\text{intersection curve})$", fontsize=14)
ax3.set_xlabel('x')
ax3.set_ylabel('z')
ax3.legend()
fig3.tight_layout()
fig3.savefig("Graphs/w8_7.png")

# Show all plots individually
plt.show()
