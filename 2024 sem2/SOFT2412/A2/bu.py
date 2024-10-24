import matplotlib.pyplot as plt
import numpy as np
import matplotlib.image as mpimg

# Load the image
img_path = 'Sprint3/Graphs/burndown_chart.png'
img = mpimg.imread(img_path)

# Plot the original image again
fig, ax = plt.subplots()
ax.imshow(img, interpolation='none')  # Disable interpolation
ax.axis('off')  # Hide axes

# Overlay the expected line
ax.plot([103.9, 553], [76.6, 412], color='red', linewidth=1, linestyle='--')

# Save the figure with higher DPI
plt.savefig("Sprint3/Graphs/burndown_chart1.png", dpi=300)

# Show the plot
plt.show()
