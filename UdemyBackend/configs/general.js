exports.jwtConfig = {
    secret : 'udemy'
}

exports.userAttributes = [
    'userName',
    'email',
    'loginType'
]

exports.categoryAttributes = [
    'id',
    'categoryName',
    'imageUrl'
]

exports.subCategoryAttributes = [
    'id',
    'categoryId',
    'subCategoryName'
]

exports.courseAttributes = [
    'id',
    'subCategoryId',
    'title',
    'tag',
    'instructorName',
    'rating',
    'peopleRated',
    'cost',
    'imageUrl'
]