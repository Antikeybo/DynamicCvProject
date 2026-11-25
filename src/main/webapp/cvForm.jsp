<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>CV Builder - Enter Details</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="form-page">
<div class="form-container">
  <h1>Dynamic Online CV Builder</h1>
  <p class="sub">Fill in your details below to generate your CV.</p>

  <!-- enctype is required so the photo file can be uploaded -->
  <form method="post" action="CVServlet" enctype="multipart/form-data">

    <!-- Photo slot -->
    <fieldset>
      <legend>Photo</legend>
      <div class="photo-slot">
        <img id="photoPreview" class="photo-preview" alt="Photo preview">
        <div id="photoPlaceholder" class="photo-placeholder">No photo</div>
      </div>
      <label>Upload Photo (JPG or PNG, max 2 MB)
        <input type="file" name="photo" id="photoInput" accept="image/*">
      </label>
    </fieldset>

    <fieldset>
      <legend>Personal Information</legend>
      <label>Full Name *<input type="text" name="fullName" required></label>
      <label>Professional Title<input type="text" name="professionalTitle"></label>
      <label>Email *<input type="email" name="email" required></label>
      <label>Phone Number<input type="tel" name="phone"></label>
      <label>LinkedIn / GitHub URL<input type="url" name="profileUrl"></label>
      <label>Profile Summary<textarea name="profileSummary" rows="3"></textarea></label>
    </fieldset>

    <fieldset>
      <legend>Education</legend>
      <label>Highest Degree *<input type="text" name="degree" required></label>
      <label>Institution Name<input type="text" name="institution"></label>
      <label>Year of Graduation<input type="text" name="graduationYear" maxlength="4" pattern="\d{4}" placeholder="e.g. 2025"></label>
      <label>Major / Field<input type="text" name="major"></label>
    </fieldset>

    <fieldset>
      <legend>Experience</legend>
      <label>Most Recent Job Title<input type="text" name="jobTitle"></label>
      <label>Company Name<input type="text" name="company"></label>
      <label>Duration<input type="text" name="duration" placeholder="e.g. Jan 2023 - Dec 2024"></label>
      <label>Key Responsibilities / Achievements (one per line)
        <textarea name="responsibilities" rows="4"></textarea></label>
    </fieldset>

    <fieldset>
      <legend>Skills</legend>
      <label>Technical Skills * (comma-separated)<input type="text" name="technicalSkills" placeholder="Java, SQL, HTML"></label>
      <label>Soft Skills (comma-separated)<input type="text" name="softSkills" placeholder="Teamwork, Communication"></label>
    </fieldset>

    <div class="actions">
      <button type="submit" class="btn">Generate My CV</button>
      <!-- GET flow: sample CV with no photo -->
      <a class="btn btn-secondary" href="CVServlet?action=sample">Generate Template</a>
    </div>
  </form>
</div>

<!-- Live photo preview + 2 MB size check -->
<script>
  document.getElementById('photoInput').addEventListener('change', function () {
    var file = this.files[0];
    var img = document.getElementById('photoPreview');
    var box = document.getElementById('photoPlaceholder');
    if (!file) { img.style.display = 'none'; box.style.display = 'flex'; return; }
    if (file.size > 2097152) {
      alert('Photo must be 2 MB or smaller.');
      this.value = '';
      img.style.display = 'none';
      box.style.display = 'flex';
      return;
    }
    img.src = URL.createObjectURL(file);
    img.style.display = 'block';
    box.style.display = 'none';
  });
</script>
</body>
</html>